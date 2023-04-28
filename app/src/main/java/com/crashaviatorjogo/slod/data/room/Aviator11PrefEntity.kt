package com.crashaviatorjogo.slod.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.crashaviatorjogo.slod.utils.Aviator11Constants.AVIATOR_11_APP_DATABASE_TABLE_PREFERENCE

@Entity(tableName = AVIATOR_11_APP_DATABASE_TABLE_PREFERENCE)
data class Aviator11PrefEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("aviator11_pref_id")
    val aviator11PrefId: Int,
    @ColumnInfo("aviator11_pref_name")
    val aviator11PrefName: String,
    @ColumnInfo("aviator11_pref_value")
    var aviator11PrefValue: Int = 0,
)