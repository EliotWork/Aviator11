package com.crashaviatorjogo.slod.domain.usecases

import com.crashaviatorjogo.slod.domain.models.WsResponse
import com.crashaviatorjogo.slod.domain.repository.Repository
import com.crashaviatorjogo.slod.domain.repository.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchSettingsUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(): Flow<Resource<WsResponse?>> {
        return repository.configSettings
    }
}