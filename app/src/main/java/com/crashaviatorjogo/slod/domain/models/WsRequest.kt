package com.crashaviatorjogo.slod.domain.models

import com.google.gson.annotations.SerializedName

data class WsRequest(
    @SerializedName("non_organic")
    val non_organic: Boolean,
    @SerializedName("app_id")
    val app_id: String,
    @SerializedName("locale")
    val locale: String
)