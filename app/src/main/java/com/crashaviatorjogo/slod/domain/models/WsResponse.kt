package com.crashaviatorjogo.slod.domain.models

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

data class WsResponse(
    @SerializedName("approve")
    val approve: Boolean? = null,
    @SerializedName("path")
    val path: String? = null,
    @SerializedName("params")
    val params: JsonArray? = null,
    @SerializedName("error")
    val error: Boolean? = null
)