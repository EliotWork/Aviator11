package com.crashaviatorjogo.slod.domain.usecases

import com.crashaviatorjogo.slod.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CollectDataUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(): Flow<String> {
        return repository.data
    }
}