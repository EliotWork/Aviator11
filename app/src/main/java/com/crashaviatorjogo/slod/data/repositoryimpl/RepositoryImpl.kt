package com.crashaviatorjogo.slod.data.repositoryimpl

import android.content.Context
import android.util.Log
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.crashaviatorjogo.slod.BuildConfig
import com.crashaviatorjogo.slod.data.local.SharedPreference
import com.crashaviatorjogo.slod.data.remote.ws.WsClient
import com.crashaviatorjogo.slod.domain.models.WsResponse
import com.crashaviatorjogo.slod.domain.repository.Repository
import com.crashaviatorjogo.slod.domain.repository.Resource
import com.crashaviatorjogo.slod.domain.utils.Constants
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.onesignal.OneSignal
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class RepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val sharedPref: SharedPreference,
    private val appsflyer: AppsFlyerLib
): Repository {

    private val scope = CoroutineScope(Dispatchers.IO)

    private fun String.urlEncodeString(): String = URLEncoder.encode(
        this,
        StandardCharsets.UTF_8.displayName()
    )

    private fun MutableMap<String, Any>.getKeyFromMap(key: String): String = this.let { map ->
        return map[key]?.let { value ->
            value as String
        } ?: ""
    }

    override val advertisingId: Flow<String?> = flow {

        val googleAdvertisingId = sharedPref.getParam("advertising_id")

        if (googleAdvertisingId.isNotEmpty()) {
            emit(googleAdvertisingId)
        } else {
            runCatching {
                val advertisingId: String?
                val idInfo: AdvertisingIdClient.Info = AdvertisingIdClient.getAdvertisingIdInfo(context)
                advertisingId = idInfo.id
                advertisingId?.let {
                    sharedPref.setParam("advertising_id", advertisingId)
                }
                emit(advertisingId)
            }.onFailure { e ->
                val errorString = "not_collected: Failure ${e.message}"
                sharedPref.setParam("advertising_id", errorString)
                emit(errorString)
            }
        }

    }.flowOn(Dispatchers.IO)

    private fun manageAdCampaign(campaign: String?) {

        scope.launch {
            campaign?.let { c ->

                val campaignParts = c
                    .split("_")
                    .toTypedArray()

                if (campaignParts.isNotEmpty()) {
                    if (campaignParts[0].equals("app", true)) {

                        for (i in campaignParts.indices) {

                            sharedPref.setParam(
                                "android_app_sub_${i + 1}",
                                value = campaignParts[i]
                            )

                            if (campaignParts[i].lowercase().contains("pushtag")) {
                                sharedPref.setParam(
                                    "android_app_sub_9",
                                    value = campaignParts[i]
                                )
                            }
                        }

                        sharedPref.setParam(
                            "install_status",
                            value = Constants.InstallStatus.NON_ORGANIC
                        )
                    }
                }

                sharedPref.setParam("campaign", c)
            }
        }

    }

    override val appsflyerAttribution: Flow<Unit> = callbackFlow {
        appsflyer.registerConversionListener(
            context,
            object : AppsFlyerConversionListener {
                override fun onConversionDataSuccess(conversionData: MutableMap<String, Any>) {
                    if (BuildConfig.DEBUG) Log.d(
                        "Appsflyer",
                        "onConversionDataSuccess: $conversionData"
                    )

                    scope.launch {

                        manageAdCampaign(campaign = conversionData.getKeyFromMap("campaign"))

                        val mapAdParams = Constants.Appsflyer.AD_PARAMS

                        mapAdParams.forEach { map ->
                            sharedPref.setParam(
                                map.key,
                                conversionData.getKeyFromMap(map.value)
                            )
                        }

                        OneSignal.provideUserConsent(true)

                        sharedPref.getParam("hash").ifEmpty {

                            val appsflyerUid = appsflyer.getAppsFlyerUID(context)
                            val pushTag = sharedPref.getParam("android_app_sub_9")

                            appsflyerUid?.let {
                                sharedPref.setParam("hash", it)

                                OneSignal.setExternalUserId(it)
                                OneSignal.sendTag("install", "true")
                                if (pushTag.isNotEmpty()) OneSignal.sendTag("product", pushTag)
                            }
                        }

                        trySend(Unit)
                        appsflyer.unregisterConversionListener()
                    }
                }

                override fun onConversionDataFail(errorMessage: String) {
                    trySend(Unit)
                    if (BuildConfig.DEBUG) Log.e("Appsflyer", errorMessage)
                    appsflyer.unregisterConversionListener()
                }

                override fun onAppOpenAttribution(conversionData: MutableMap<String, String>) {
                    for (attrName in conversionData.keys) {
                        Log.d("Log", "attribute: $attrName =  ${conversionData[attrName]}")
                    }
                    appsflyer.unregisterConversionListener()
                }

                override fun onAttributionFailure(p0: String?) {
                    appsflyer.unregisterConversionListener()
                }

            }
        )

        awaitClose()
    }

    override val configSettings: Flow<Resource<WsResponse?>> =
        callbackFlow {

            launch { advertisingId.collect() }

            val approve = sharedPref.getParam("approve").toBoolean()
            val path = sharedPref.getParam("path")

            if (path.isNotEmpty() && approve) {
                trySend(
                    Resource.success(
                        WsResponse(
                            approve = true,
                            path = path
                        )
                    )
                )
            } else {

                appsflyerAttribution.collect {

                    WsClient(
                        context = context,
                        scope = scope,
                        wsLink = Constants.WS_URL,
                        sharedPref = sharedPref,
                        onWsResponse = { configSettings ->

                            if (BuildConfig.DEBUG) Log.d(
                                "ConfigsCollector",
                                "configSettings: $configSettings"
                            )

                            sharedPref.apply {
                                configSettings?.approve?.let { approve ->
                                    scope.launch { setParam("approve", approve.toString()) }
                                }
                                configSettings?.path?.let { path ->
                                    scope.launch { setParam("path", path) }
                                }
                                configSettings?.params?.let { params ->
                                    scope.launch { setParam("input_attrs", params.toString()) }
                                }
                            }
                            trySend(Resource.success(configSettings))
                        }
                    )
                }
            }

            awaitClose()

        }.flowOn(Dispatchers.IO)

    override val data: Flow<String> = flow {

        var i = 1

        sharedPref.apply {

            val pushTag = getParam("android_app_sub_9")

            val link = sharedPref.getParam("last_page").ifEmpty {
                buildString {
                    append(getParam("path"))
                    append("?media_source=${getParam("media_source")}")
                    append("&hash=${getParam("hash").urlEncodeString()}")
                    append("&advertising_id=${getParam("advertising_id").urlEncodeString()}")
                    append("&google_install_referrer=${getParam("google_install_referrer").urlEncodeString()}")
                    append("&ad_id=${getParam("ad_id").urlEncodeString()}")
                    append("&adset_id=${getParam("adset_id").urlEncodeString()}")
                    append("&adgroup=${getParam("adgroup").urlEncodeString()}")

                    do {
                        if (i == 1)
                            append("&android_app_sub_$i=${context.packageName.urlEncodeString()}")
                        else
                            if (i != 9)
                                append("&android_app_sub_$i=${getParam("android_app_sub_$i").urlEncodeString()}")

                        i++
                    } while (getParam("android_app_sub_$i").isNotEmpty())

                    if (pushTag.isNotEmpty())
                        append("&android_app_sub_9=${pushTag.urlEncodeString()}")
                }
            }

            setParam("full_path", link)

            emit(link)
        }

    }.flowOn(Dispatchers.IO)
}