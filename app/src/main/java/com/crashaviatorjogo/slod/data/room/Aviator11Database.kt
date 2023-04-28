package com.crashaviatorjogo.slod.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Aviator11BgEntity::class,
        Aviator11PlaneEntity::class,
        Aviator11PrefEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class Aviator11AppDatabase: RoomDatabase() {

    abstract fun aviator11Dao(): Aviator11Dao
}