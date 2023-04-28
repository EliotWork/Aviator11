package com.crashaviatorjogo.slod.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.crashaviatorjogo.slod.utils.Aviator11Constants.AVIATOR_11_APP_DATABASE_TABLE_PLANE

@Entity(tableName = AVIATOR_11_APP_DATABASE_TABLE_PLANE)
data class Aviator11PlaneEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("aviator11_plane_id")
    val aviator11PlaneId: Int,
    @ColumnInfo("aviator11_plane_skin")
    val aviator11PlaneSkin: Int,
    @ColumnInfo("aviator11_plane_available")
    val aviator11PlaneAvailable: Boolean,
)