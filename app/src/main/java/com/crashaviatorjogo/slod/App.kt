package com.crashaviatorjogo.slod

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.crashaviatorjogo.slod.domain.utils.Constants.ONESIGNAL_APP_ID
import com.onesignal.OneSignal
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        OneSignal.initWithContext(applicationContext)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
        createNotificationChannel(applicationContext)
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val id = context.packageName
            val description = "Notification"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id, "Notification channel", importance)
            channel.description = description
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}