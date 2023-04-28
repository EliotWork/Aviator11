package com.crashaviatorjogo.slod.di

import com.crashaviatorjogo.slod.data.repositoryimpl.RepositoryImpl
import com.crashaviatorjogo.slod.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Binds
    fun provideRepository(impl: RepositoryImpl): Repository
}