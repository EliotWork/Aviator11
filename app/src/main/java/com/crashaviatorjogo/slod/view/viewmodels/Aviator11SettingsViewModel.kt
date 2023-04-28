package com.crashaviatorjogo.slod.view.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crashaviatorjogo.slod.R
import com.crashaviatorjogo.slod.data.room.Aviator11BgEntity
import com.crashaviatorjogo.slod.data.room.Aviator11DBHelper
import com.crashaviatorjogo.slod.data.room.Aviator11PlaneEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Aviator11SettingsViewModel @Inject constructor(
    private val dbHelper: Aviator11DBHelper
): ViewModel() {

    var aviator11Background by mutableStateOf(R.drawable.background02)
    var aviator11Plane by mutableStateOf(R.drawable.skin01)
    var aviator11Coins by mutableStateOf(0)
    var aviator11BgList by mutableStateOf(listOf(
        Aviator11BgEntity(1, R.drawable.background02,true)
    ))
    var aviator11BgItem by mutableStateOf(0)
    var aviator11PlaneList by mutableStateOf(listOf(
        Aviator11PlaneEntity(1, R.drawable.skin01, true)
    ))
    var aviator11PlaneItem by mutableStateOf(0)


    init {
        aviator11InitLists()
        aviator11GetCoins()
        aviator11GetBackground()
        aviator11GetPlane()
    }

    fun aviator11SaveSettings() {
        viewModelScope.launch {
            dbHelper.aviator11AddNewValueForPrefBg(aviator11Background)
            dbHelper.aviator11AddNewValueForPrefPlane(aviator11Plane)
        }
    }

    fun aviator11SelectBackground() {
        aviator11Background = aviator11BgList[aviator11BgItem].aviator11BgSkin
    }

    fun aviator11SelectPlane() {
        aviator11Plane = aviator11PlaneList[aviator11PlaneItem].aviator11PlaneSkin
    }

    private fun aviator11GetCoins() {
        viewModelScope.launch {
            dbHelper.aviator11SelectCurrentPreference("coins").collect {
                aviator11Coins = it.aviator11PrefValue
            }
        }
    }

    private fun aviator11GetBackground() {
        viewModelScope.launch {
            dbHelper.aviator11SelectCurrentPreference("background").collect {
                aviator11Background = it.aviator11PrefValue
            }
        }
    }

    private fun aviator11GetPlane() {
        viewModelScope.launch {
            dbHelper.aviator11SelectCurrentPreference("plane").collect {
                aviator11Plane = it.aviator11PrefValue
            }
        }
    }

    fun aviator11ChangeBgItem(aviator11NextItem: Boolean) {
        if (aviator11BgList.size!=1) {
            when(aviator11NextItem) {
                true -> {
                    if ((aviator11BgItem+1)<aviator11BgList.size) aviator11BgItem+=1
                    else aviator11BgItem = 0
                }
                false -> {
                    if ((aviator11BgItem-1)>=0) aviator11BgItem-=1
                    else aviator11BgItem = aviator11BgList.size-1
                }
            }
        }
    }

    fun aviator11ChangePlaneItem(aviator11NextItem: Boolean) {
        if (aviator11PlaneList.size!=1) {
            when(aviator11NextItem) {
                true -> {
                    if ((aviator11PlaneItem+1)<aviator11PlaneList.size) aviator11PlaneItem+=1
                    else aviator11PlaneItem = 0
                }
                false -> {
                    if ((aviator11PlaneItem-1)>=0) aviator11PlaneItem-=1
                    else aviator11PlaneItem = aviator11PlaneList.size-1
                }
            }
        }
    }

    private fun aviator11InitLists() {
        viewModelScope.launch {
            dbHelper.aviator11AvailableBackgrounds().collect { aviator11BgList = it }
        }
        viewModelScope.launch {
            dbHelper.aviator11AvailablePlanes().collect { aviator11PlaneList = it }
        }
    }
}