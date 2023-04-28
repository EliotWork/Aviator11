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
class Aviator11ShopViewModel @Inject constructor(
    private val dbHelper: Aviator11DBHelper
): ViewModel() {

    var aviator11Background by mutableStateOf(R.drawable.background02)
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

    fun aviator11PurchaseBackground() {
        if (aviator11Coins>=100) {
            viewModelScope.launch {
                dbHelper.aviator11SetBackgroundAvailable(aviator11BgList[aviator11BgItem].aviator11BgId)
                aviator11Coins-=100
                dbHelper.aviator11AddNewValueForPrefCoin(aviator11Coins)
            }
        }
    }

    fun aviator11PurchasePlane() {
        if (aviator11Coins>=100) {
            viewModelScope.launch {
                dbHelper.aviator11SetPlaneAvailable(aviator11PlaneList[aviator11PlaneItem].aviator11PlaneId)
                aviator11Coins-=100
                dbHelper.aviator11AddNewValueForPrefCoin(aviator11Coins)
            }
        }
    }

    fun aviator11ChangeBgItem(aviator11NextItem: Boolean) {
        when(aviator11NextItem) {
            true -> {
                if ((aviator11BgItem+1)<6) aviator11BgItem+=1
                else aviator11BgItem = 0
            }
            false -> {
                if ((aviator11BgItem-1)>=0) aviator11BgItem-=1
                else aviator11BgItem = 5
            }
        }
    }

    fun aviator11ChangePlaneItem(aviator11NextItem: Boolean) {
        when(aviator11NextItem) {
            true -> {
                if ((aviator11PlaneItem+1)<6) aviator11PlaneItem+=1
                else aviator11PlaneItem = 0
            }
            false -> {
                if ((aviator11PlaneItem-1)>=0) aviator11PlaneItem-=1
                else aviator11PlaneItem = 5
            }
        }
    }

    private fun aviator11InitLists() {
        viewModelScope.launch {
            dbHelper.aviator11AllBackgrounds().collect { aviator11BgList = it }
        }
        viewModelScope.launch {
            dbHelper.aviator11AllPlanes().collect { aviator11PlaneList = it }
        }
    }
}