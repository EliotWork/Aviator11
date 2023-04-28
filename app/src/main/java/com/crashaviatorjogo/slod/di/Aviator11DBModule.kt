package com.crashaviatorjogo.slod.di

import android.content.Context
import androidx.room.Room
import com.crashaviatorjogo.slod.data.room.Aviator11AppDatabase
import com.crashaviatorjogo.slod.data.room.Aviator11Dao
import com.crashaviatorjogo.slod.utils.Aviator11Constants.AVIATOR_11_APP_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Aviator11DBModule {

    @Provides
    @Singleton
    fun aviator11ProvideDatabase(@ApplicationContext context: Context): Aviator11AppDatabase {
        return Room.databaseBuilder(context,
            Aviator11AppDatabase::class.java,
            AVIATOR_11_APP_DATABASE_NAME
            ).build()
    }

    @Provides
    @Singleton
    fun aviator11ProvideDao(aviator11AppDatabase: Aviator11AppDatabase): Aviator11Dao {
        return aviator11AppDatabase.aviator11Dao()
    }
}