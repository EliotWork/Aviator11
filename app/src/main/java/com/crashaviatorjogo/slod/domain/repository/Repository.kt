package com.crashaviatorjogo.slod.domain.repository

import com.crashaviatorjogo.slod.domain.models.WsResponse
import kotlinx.coroutines.flow.Flow

interface Repository {

    val appsflyerAttribution: Flow<Unit>

    val configSettings: Flow<Resource<WsResponse?>>

    val advertisingId: Flow<String?>

    val data: Flow<String>


}

