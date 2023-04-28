package com.crashaviatorjogo.slod.data.room

import androidx.room.*
import com.crashaviatorjogo.slod.utils.Aviator11Constants.AVIATOR_11_APP_DATABASE_TABLE_BACKGROUND
import com.crashaviatorjogo.slod.utils.Aviator11Constants.AVIATOR_11_APP_DATABASE_TABLE_PLANE
import com.crashaviatorjogo.slod.utils.Aviator11Constants.AVIATOR_11_APP_DATABASE_TABLE_PREFERENCE
import kotlinx.coroutines.flow.Flow

@Dao
interface Aviator11Dao {

    @Query("SELECT * FROM $AVIATOR_11_APP_DATABASE_TABLE_BACKGROUND")
    fun aviator11AllBackgrounds(): Flow<List<Aviator11BgEntity>>

    @Query("SELECT * FROM $AVIATOR_11_APP_DATABASE_TABLE_BACKGROUND WHERE aviator11_bg_available LIKE true")
    fun aviator11AvailableBackgrounds(): Flow<List<Aviator11BgEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun aviator11AddNewBackground(aviator11BgEntity: Aviator11BgEntity)

    @Query("UPDATE $AVIATOR_11_APP_DATABASE_TABLE_BACKGROUND SET aviator11_bg_available = true WHERE aviator11_bg_id =:aviator11BgId")
    suspend fun aviator11SetBackgroundAvailable(aviator11BgId: Int)

    @Query("SELECT * FROM $AVIATOR_11_APP_DATABASE_TABLE_PLANE")
    fun aviator11AllPlanes(): Flow<List<Aviator11PlaneEntity>>

    @Query("SELECT * FROM $AVIATOR_11_APP_DATABASE_TABLE_PLANE WHERE aviator11_plane_available LIKE true")
    fun aviator11AvailablePlanes(): Flow<List<Aviator11PlaneEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun aviator11AddNewPlane(aviator11PlaneEntity: Aviator11PlaneEntity)

    @Query("UPDATE $AVIATOR_11_APP_DATABASE_TABLE_PLANE SET aviator11_plane_available = true WHERE aviator11_plane_id =:aviator11PlaneId")
    suspend fun aviator11SetPlaneAvailable(aviator11PlaneId: Int)

    @Query("SELECT * FROM $AVIATOR_11_APP_DATABASE_TABLE_PREFERENCE WHERE aviator11_pref_name =:prefName")
    fun aviator11SelectCurrentPreference(prefName: String): Flow<Aviator11PrefEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun aviator11AddNewPref(aviator11PrefEntity: Aviator11PrefEntity)

    @Upsert
    suspend fun aviator11AddNewValueForPref(aviator11PrefEntity: Aviator11PrefEntity)
}