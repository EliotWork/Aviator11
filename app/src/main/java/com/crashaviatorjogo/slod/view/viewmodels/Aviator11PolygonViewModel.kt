package com.crashaviatorjogo.slod.view.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crashaviatorjogo.slod.R
import com.crashaviatorjogo.slod.data.room.Aviator11DBHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class Aviator11PolygonViewModel @Inject constructor(
    private val dbHelper: Aviator11DBHelper
): ViewModel() {

    //Visibility
    var aviator11DoneScreenVisibility by mutableStateOf(false)
    var aviator11PauseScreenVisibility by mutableStateOf(false)

    //Prefs
    var aviator11Plane by mutableStateOf(R.drawable.skin01)
    var aviator11PlaneAlpha by mutableStateOf(1f)
    var aviator11Background by mutableStateOf(R.drawable.background02)
    var aviator11TotalCoins by mutableStateOf(0)
    var aviator11BestRecord by mutableStateOf(0)

    //Polygon
    var aviator11Lives by mutableStateOf(3)
    var aviator11Score by mutableStateOf(0)
    var aviator11PolygonCoins by mutableStateOf(0)
    var aviator11Paused by mutableStateOf(false)
    var aviator11ControlBtnsAlpha by mutableStateOf(1f)

    var aviator11PlaneX by mutableStateOf(3)
    var aviator11XCoin1X by mutableStateOf(1)
    var aviator11XCoin2X by mutableStateOf(1)
    var aviator11XCoin3X by mutableStateOf(1)
    var aviator11XCoinsX by mutableStateOf(5)
    var aviator11XBarrier1X by mutableStateOf(1)
    var aviator11XBarrier2X by mutableStateOf(5)
    var aviator11XBarrier3X by mutableStateOf(5)
    var aviator11XBarrier4X by mutableStateOf(5)

    var aviator11XCoin1Yanim by mutableStateOf(false)
    var aviator11XCoin2Yanim by mutableStateOf(false)
    var aviator11XCoin3Yanim by mutableStateOf(false)
    var aviator11XCoinsYanim by mutableStateOf(false)
    var aviator11XBarrier1Yanim by mutableStateOf(false)
    var aviator11XBarrier2Yanim by mutableStateOf(false)
    var aviator11XBarrier3Yanim by mutableStateOf(false)
    var aviator11XBarrier4Yanim by mutableStateOf(false)

    var aviator11XCoin1Yalpha by mutableStateOf(0f)
    var aviator11XCoin2Yalpha by mutableStateOf(0f)
    var aviator11XCoin3Yalpha by mutableStateOf(0f)
    var aviator11XCoinsYalpha by mutableStateOf(0f)
    var aviator11XBarrier1Yalpha by mutableStateOf(0f)
    var aviator11XBarrier2Yalpha by mutableStateOf(0f)
    var aviator11XBarrier3Yalpha by mutableStateOf(0f)
    var aviator11XBarrier4Yalpha by mutableStateOf(0f)

    init {
        aviator11GetPlane()
        aviator11GetBackground()
        aviator11GetCoins()
        aviator11GetBestRecord()
    }

    fun aviator11Coin1FinishListener() {
        if (aviator11XCoin1Yanim) {
            aviator11XCoin1Yanim = false
            if (!aviator11Paused) {
                aviator11XCoin1Yalpha = 1f
                viewModelScope.launch {
                    delay(2600)
                    if (aviator11XCoin1X==aviator11PlaneX) {
                        aviator11XCoin1Yalpha = 0f
                        aviator11TouchCoins(1)
                    }
                }
            }
        } else {
            aviator11XCoin1X = Random.nextInt(7)
            aviator11XCoin1Yalpha = 0f
            aviator11XCoin1Yanim = true
        }
    }

    fun aviator11Coin2FinishListener() {
        if (aviator11XCoin2Yanim) {
            aviator11XCoin2Yanim = false
            if (!aviator11Paused) {
                aviator11XCoin2Yalpha = 1f
                viewModelScope.launch {
                    delay(2600)
                    if (aviator11XCoin2X==aviator11PlaneX) {
                        aviator11XCoin2Yalpha = 0f
                        aviator11TouchCoins(1)
                    }
                }
            }
        } else {
            aviator11XCoin2X = Random.nextInt(7)
            aviator11XCoin2Yalpha = 0f
            aviator11XCoin2Yanim = true
        }
    }

    fun aviator11Coin3FinishListener() {
        if (aviator11XCoin3Yanim) {
            aviator11XCoin3Yanim = false
            if (!aviator11Paused) {
                aviator11XCoin3Yalpha = 1f
                viewModelScope.launch {
                    delay(2600)
                    if (aviator11XCoin3X==aviator11PlaneX) {
                        aviator11XCoin3Yalpha = 0f
                        aviator11TouchCoins(1)
                    }
                }
            }
        } else {
            aviator11XCoin3X = Random.nextInt(7)
            aviator11XCoin3Yalpha = 0f
            aviator11XCoin3Yanim = true
        }
    }

    fun aviator11CoinsFinishListener() {
        if (aviator11XCoinsYanim) {
            aviator11XCoinsYanim = false
            if (!aviator11Paused) {
                aviator11XCoinsYalpha = 1f
                viewModelScope.launch {
                    delay(2600)
                    if (aviator11XCoinsX==aviator11PlaneX) {
                        aviator11XCoinsYalpha = 0f
                        aviator11TouchCoins(3)
                    }
                }
            }
        } else {
            aviator11XCoinsX = Random.nextInt(7)
            aviator11XCoinsYalpha = 0f
            aviator11XCoinsYanim = true
        }
    }

    fun aviator11Barrier1FinishListener() {
        if (aviator11XBarrier1Yanim) {
            aviator11XBarrier1Yanim = false
            if (!aviator11Paused) {
                aviator11XBarrier1Yalpha = 1f
                viewModelScope.launch {
                    delay(2600)
                    when (aviator11XBarrier1X) {
                        1 -> { if (aviator11PlaneX<5) aviator11TouchBarrier() }
                        5 -> { if (aviator11PlaneX>1) aviator11TouchBarrier()  }
                        else -> aviator11SuccessOvercoming()
                    }
                }
            }
        } else {
            aviator11XBarrier1X = if (Random.nextBoolean()) 5 else 1
            aviator11XBarrier1Yalpha = 0f
            aviator11XBarrier1Yanim = true
        }
    }

    fun aviator11Barrier2FinishListener() {
        if (aviator11XBarrier2Yanim) {
            aviator11XBarrier2Yanim = false
            if (!aviator11Paused) {
                aviator11XBarrier2Yalpha = 1f
                viewModelScope.launch {
                    delay(2600)
                    if (aviator11XBarrier2X in aviator11PlaneX-1..aviator11PlaneX+1) aviator11TouchBarrier()
                    else aviator11SuccessOvercoming()
                }
            }
        } else {
            aviator11XBarrier2X = if (Random.nextBoolean()) 5 else 1
            aviator11XBarrier2Yalpha = 0f
            aviator11XBarrier2Yanim = true
        }
    }

    fun aviator11Barrier3FinishListener() {
        if (aviator11XBarrier3Yanim) {
            aviator11XBarrier3Yanim = false
            if (!aviator11Paused) {
                aviator11XBarrier3Yalpha = 1f
                viewModelScope.launch {
                    delay(2600)
                    if (aviator11XBarrier3X==aviator11PlaneX) aviator11TouchBarrier()
                    else aviator11SuccessOvercoming()
                }
            }
        } else {
            aviator11XBarrier3X = if (Random.nextBoolean()) 5 else 1
            aviator11XBarrier3Yalpha = 0f
            aviator11XBarrier3Yanim = true
        }
    }

    fun aviator11Barrier4FinishListener() {
        if (aviator11XBarrier4Yanim) {
            aviator11XBarrier4Yanim = false
            if (!aviator11Paused) {
                aviator11XBarrier4Yalpha = 1f
                viewModelScope.launch {
                    delay(2600)
                    if (aviator11XBarrier4X==aviator11PlaneX) { aviator11TouchBarrier() }
                    else aviator11SuccessOvercoming()
                }
            }
        } else {
            aviator11XBarrier4X = if (Random.nextBoolean()) 5 else 1
            aviator11XBarrier4Yalpha = 0f
            aviator11XBarrier4Yanim = true
        }
    }

    fun aviator11ChangePosition(rightArrow: Boolean) {
        when (rightArrow) {
            true -> { if ((aviator11PlaneX+1)<=6) aviator11PlaneX+=1 }
            false -> { if ((aviator11PlaneX-1)>=0) aviator11PlaneX-=1 }
        }
    }

    private fun aviator11GetPlane() {
        viewModelScope.launch {
            dbHelper.aviator11SelectCurrentPreference("plane").collect {
                aviator11Plane = it.aviator11PrefValue
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

    private fun aviator11GetCoins() {
        viewModelScope.launch {
            dbHelper.aviator11SelectCurrentPreference("coins").collect {
                aviator11TotalCoins = it.aviator11PrefValue
            }
        }
    }

    private fun aviator11GetBestRecord() {
        viewModelScope.launch {
            dbHelper.aviator11SelectCurrentPreference("best_record").collect {
                aviator11BestRecord = it.aviator11PrefValue
            }
        }
    }

    fun aviator11DoneScreenAction() {
        viewModelScope.launch {
            if (aviator11Score>=aviator11BestRecord) {
                dbHelper.aviator11AddNewValueForPrefBestRecord(aviator11Score)
            }
            dbHelper.aviator11AddNewValueForPrefCoin(aviator11TotalCoins+aviator11PolygonCoins)
        }
    }

    private fun aviator11TouchBarrier() {
        if (!aviator11Paused) {
            aviator11Lives-=1
            if (aviator11Lives==0) {
                aviator11ControlBtnsAlpha = 0f
                aviator11DoneScreenVisibility = true
                aviator11FinishGame()
                aviator11Paused = !aviator11Paused
            }
            viewModelScope.launch {
                aviator11PlaneAlpha = 0f
                delay(100)
                aviator11PlaneAlpha = 1f
                delay(100)
                aviator11PlaneAlpha = 0f
                delay(100)
                aviator11PlaneAlpha = 1f
                delay(100)
                aviator11PlaneAlpha = 0f
                delay(100)
                aviator11PlaneAlpha = 1f
                delay(100)
                aviator11PlaneAlpha = 0f
                delay(100)
                aviator11PlaneAlpha = 1f
            }
        }
    }

    private fun aviator11TouchCoins(aviator11CoinsQty: Int) {
        if (!aviator11Paused) aviator11PolygonCoins += aviator11CoinsQty
    }

    private fun aviator11SuccessOvercoming() { if (!aviator11Paused) aviator11Score+=1 }

    fun aviator11StartFlight() {
        viewModelScope.launch {
            aviator11XCoin1Yanim = true
            delay(1000)
            aviator11XBarrier3Yanim = true
            delay(1000)
            aviator11XCoinsYanim = true
            delay(1000)
            aviator11XBarrier1Yanim = true
            delay(1000)
            aviator11XCoin2Yanim = true
            delay(1000)
            aviator11XBarrier2Yanim = true
            delay(1000)
            aviator11XCoin3Yanim = true
            delay(1000)
            aviator11XBarrier4Yanim = true
        }
    }

    fun aviator11PauseFlight() {
        aviator11Paused = !aviator11Paused
        if (aviator11Paused) {
            aviator11PauseScreenVisibility = true
            aviator11FinishGame()
        } else {
            aviator11PauseScreenVisibility = false
        }
    }

    private fun aviator11FinishGame() {
        aviator11XCoin1Yalpha = 0f
        aviator11XCoin2Yalpha = 0f
        aviator11XCoin3Yalpha = 0f
        aviator11XCoinsYalpha = 0f
        aviator11XBarrier1Yalpha = 0f
        aviator11XBarrier2Yalpha = 0f
        aviator11XBarrier3Yalpha = 0f
        aviator11XBarrier4Yalpha = 0f
    }
}