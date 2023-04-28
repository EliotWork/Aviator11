package com.crashaviatorjogo.slod.di

import android.content.Context
import com.appsflyer.AppsFlyerLib
import com.crashaviatorjogo.slod.domain.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.crashaviatorjogo.slod.BuildConfig

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAppsflyer(@ApplicationContext context: Context): AppsFlyerLib{
        val appsflyerLib = AppsFlyerLib.getInstance()
        appsflyerLib.init(Constants.APPSFLYER_DEV_KEY,null,context)
        appsflyerLib.start(context,Constants.APPSFLYER_DEV_KEY)
        if(BuildConfig.DEBUG) appsflyerLib.setDebugLog(true)
        return appsflyerLib
    }
}