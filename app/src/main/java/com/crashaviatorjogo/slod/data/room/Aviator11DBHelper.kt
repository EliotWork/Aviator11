package com.crashaviatorjogo.slod.data.room

import com.crashaviatorjogo.slod.R
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Aviator11DBHelper @Inject constructor(
    private val aviator11Dao: Aviator11Dao
) {

    fun aviator11AllBackgrounds(): Flow<List<Aviator11BgEntity>>
        = aviator11Dao.aviator11AllBackgrounds()

    fun aviator11AvailableBackgrounds(): Flow<List<Aviator11BgEntity>>
        = aviator11Dao.aviator11AvailableBackgrounds()

    suspend fun aviator11AddNewBackground(aviator11BgEntity: Aviator11BgEntity)
        = aviator11Dao.aviator11AddNewBackground(aviator11BgEntity)

    suspend fun aviator11SetBackgroundAvailable(aviator11BgId: Int)
        = aviator11Dao.aviator11SetBackgroundAvailable(aviator11BgId)

    fun aviator11AllPlanes(): Flow<List<Aviator11PlaneEntity>>
        = aviator11Dao.aviator11AllPlanes()

    fun aviator11AvailablePlanes(): Flow<List<Aviator11PlaneEntity>>
        = aviator11Dao.aviator11AvailablePlanes()

    suspend fun aviator11AddNewPlane(aviator11PlaneEntity: Aviator11PlaneEntity)
        = aviator11Dao.aviator11AddNewPlane(aviator11PlaneEntity)

    suspend fun aviator11SetPlaneAvailable(aviator11BgId: Int)
        = aviator11Dao.aviator11SetPlaneAvailable(aviator11BgId)

    fun aviator11SelectCurrentPreference(prefName: String): Flow<Aviator11PrefEntity>
        = aviator11Dao.aviator11SelectCurrentPreference(prefName)

    suspend fun aviator11AddNewPref(aviator11PrefEntity: Aviator11PrefEntity)
        = aviator11Dao.aviator11AddNewPref(aviator11PrefEntity)

    suspend fun aviator11AddNewValueForPrefBg(newValue: Int) {
        aviator11Dao.aviator11AddNewValueForPref(Aviator11PrefEntity(1, "background", newValue))
    }

    suspend fun aviator11AddNewValueForPrefPlane(newValue: Int) {
        aviator11Dao.aviator11AddNewValueForPref(Aviator11PrefEntity(2, "plane", newValue))
    }

    suspend fun aviator11AddNewValueForPrefCoin(newValue: Int) {
        aviator11Dao.aviator11AddNewValueForPref(Aviator11PrefEntity(3, "coins", newValue))
    }

    suspend fun aviator11AddNewValueForPrefBestRecord(newValue: Int) {
        aviator11Dao.aviator11AddNewValueForPref(Aviator11PrefEntity(4, "best_record", newValue))
    }

    suspend fun aviator11InitDatabase() {
        aviator11Dao.aviator11AddNewPref(Aviator11PrefEntity(1, "background", R.drawable.background02))
        aviator11Dao.aviator11AddNewPref(Aviator11PrefEntity(2, "plane", R.drawable.skin02))
        aviator11Dao.aviator11AddNewPref(Aviator11PrefEntity(3, "coins", 0))
        aviator11Dao.aviator11AddNewPref(Aviator11PrefEntity(4, "best_record", 0))

        aviator11Dao.aviator11AddNewBackground(Aviator11BgEntity(1, R.drawable.background02,true))
        aviator11Dao.aviator11AddNewBackground(Aviator11BgEntity(2, R.drawable.background05,false))
        aviator11Dao.aviator11AddNewBackground(Aviator11BgEntity(3, R.drawable.background06,false))
        aviator11Dao.aviator11AddNewBackground(Aviator11BgEntity(4, R.drawable.background07,false))
        aviator11Dao.aviator11AddNewBackground(Aviator11BgEntity(5, R.drawable.background08,false))
        aviator11Dao.aviator11AddNewBackground(Aviator11BgEntity(6, R.drawable.background09,false))

        aviator11Dao.aviator11AddNewPlane(Aviator11PlaneEntity(1, R.drawable.skin01, true))
        aviator11Dao.aviator11AddNewPlane(Aviator11PlaneEntity(2, R.drawable.skin02, false))
        aviator11Dao.aviator11AddNewPlane(Aviator11PlaneEntity(3, R.drawable.skin03, false))
        aviator11Dao.aviator11AddNewPlane(Aviator11PlaneEntity(4, R.drawable.skin04, false))
        aviator11Dao.aviator11AddNewPlane(Aviator11PlaneEntity(5, R.drawable.skin05, false))
        aviator11Dao.aviator11AddNewPlane(Aviator11PlaneEntity(6, R.drawable.skin06, false))
    }
}