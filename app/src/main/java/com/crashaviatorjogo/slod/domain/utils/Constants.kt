package com.crashaviatorjogo.slod.domain.utils

import com.crashaviatorjogo.slod.view.activity.MainActivity


object Constants {
    const val APPSFLYER_DEV_KEY = "siihohesXyh9ufznp2X9mg"

    const val ONESIGNAL_APP_ID = "134368ef-c936-4a81-abcc-faea7507fc8a"
    val GAME_CLASS = MainActivity::class.java

    const val WS_URL = "wss://app.crashaviatorjogo.life/check"
    const val SHARED_PREFERENCE = "aviator11"

    object InstallStatus {
        const val NON_ORGANIC = "non_organic"
    }

    object Appsflyer {
        val AD_PARAMS = mapOf(
            "af_status" to "af_status",
            "media_source" to "media_source",
            "media_source" to "agency",
            "ad_id" to "ad_id",
            "adset_id" to "adset_id",
            "adgroup" to "adgroup",
            "adset" to "adset",
            "campaign_id" to "campaign_id"
        )
    }
}