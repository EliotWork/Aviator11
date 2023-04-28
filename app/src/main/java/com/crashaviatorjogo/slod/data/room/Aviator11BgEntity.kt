package com.crashaviatorjogo.slod.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.crashaviatorjogo.slod.utils.Aviator11Constants.AVIATOR_11_APP_DATABASE_TABLE_BACKGROUND

@Entity(tableName = AVIATOR_11_APP_DATABASE_TABLE_BACKGROUND)
data class Aviator11BgEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("aviator11_bg_id")
    val aviator11BgId: Int,
    @ColumnInfo("aviator11_bg_skin")
    val aviator11BgSkin: Int,
    @ColumnInfo("aviator11_bg_available")
    val aviator11BgAvailable: Boolean
)