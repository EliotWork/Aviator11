package com.crashaviatorjogo.slod.data.remote.ws

import android.content.Context
import android.os.Build
import com.crashaviatorjogo.slod.data.local.SharedPreference
import com.crashaviatorjogo.slod.domain.models.WsRequest
import com.crashaviatorjogo.slod.domain.models.WsResponse
import com.crashaviatorjogo.slod.domain.utils.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.net.URI

class WsClient(
    context: Context,
    scope: CoroutineScope,
    wsLink: String,
    sharedPref: SharedPreference,
    onWsResponse: OnWsResponse
) {

    private lateinit var webSocketClient: WebSocketClient

    init {

        webSocketClient = object : WebSocketClient(URI(wsLink)) {

            override fun onOpen(handshakedata: ServerHandshake?) {

                scope.launch {

                    val installStatus = sharedPref.getParam("install_status")
                    val googleAdvertisingId = sharedPref.getParam("advertising_id")
                    webSocketClient.send(
                        Gson().toJson(
                            WsRequest(
                                non_organic = installStatus == Constants.InstallStatus.NON_ORGANIC,
                                app_id = context.packageName,
                                locale = getCurrentLocaleCountry(context = context)
                            ),
                            object: TypeToken<WsRequest>() {}.type
                        ).toByteArray()
                    )

                }
            }

            override fun onMessage(message: String?) {
                message?.let { jsonString ->
                    val type = object : TypeToken<WsResponse>() {}.type
                    val wsResponse: WsResponse? = Gson().fromJson<WsResponse>(jsonString, type)

                    onWsResponse.onResponse(wsResponse = wsResponse)
                }?: onWsResponse.onResponse(wsResponse = null)
            }

            override fun onClose(code: Int, reason: String?, remote: Boolean) {
                webSocketClient.close()
            }

            override fun onError(ex: Exception?) {
                onWsResponse.onResponse(wsResponse = null)
            }
        }
        webSocketClient.connect()
    }

    fun interface OnWsResponse {
        fun onResponse(wsResponse: WsResponse?)
    }

    @Suppress("DEPRECATION")
    private fun getCurrentLocaleCountry(context: Context): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.configuration.locales.get(0).language.lowercase()
        } else {
            context.resources.configuration.locale.country.lowercase()
        }
    }
}