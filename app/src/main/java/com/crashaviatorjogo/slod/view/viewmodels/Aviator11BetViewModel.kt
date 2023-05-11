package com.crashaviatorjogo.slod.view.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crashaviatorjogo.slod.R
import com.crashaviatorjogo.slod.data.room.Aviator11DBHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class Aviator11BetViewModel @Inject constructor(
    private val dbHelper: Aviator11DBHelper,
    @ApplicationContext private val context: Context
): ViewModel() {
    var aviator11Background by mutableStateOf(R.drawable.background02)
    var aviator11Coins by mutableStateOf(0)

    var aviator11Bet by mutableStateOf(5)
    var aviator11BetX by mutableStateOf(1)

    var aviator11MachineSpinning by mutableStateOf(false)
    var aviator11MachinePos1 by mutableStateOf(0)
    var aviator11MachinePos2 by mutableStateOf(0)
    var aviator11MachinePos3 by mutableStateOf(0)

    val aviator11Array: Array<Int> = Array(250) { Random.nextInt(0, 5) }

    init {
        aviator11GetCoins()
        aviator11GetBackground()
        aviator11AddMachineIndexes()
    }

    fun aviator11AddMachineIndexes() {
        if ((aviator11MachinePos1+2)<=240) aviator11MachinePos1 += 2
        else aviator11MachinePos1 = 0
        if ((aviator11MachinePos2+4)<=240) aviator11MachinePos2 += 4
        else aviator11MachinePos2 = 0
        if ((aviator11MachinePos3+6)<=240) aviator11MachinePos3 += 6
        else aviator11MachinePos3 = 0
    }

    private val aviator11BetZeroToast = Toast.makeText(context, R.string.aviator11_rate_zero, Toast.LENGTH_SHORT)
    private val aviator11BetOverToast = Toast.makeText(context, R.string.aviator11_rate_over, Toast.LENGTH_SHORT)
    fun aviator11Spin(): Boolean {
        if ((aviator11Bet*aviator11BetX)<=aviator11Coins) {
            if (aviator11Bet>0) {
                viewModelScope.launch {
                    aviator11MachineSpinning = true
                    aviator11Coins -= aviator11Bet*aviator11BetX
                    dbHelper.aviator11AddNewValueForPrefCoin(aviator11Coins)
                }
                return true
            } else aviator11BetZeroToast.show()
        } else aviator11BetOverToast.show()
        return false
    }

    fun aviator11CalculateCoins() {
        viewModelScope.launch {
            aviator11Coins += aviator11CheckIndexes()
            dbHelper.aviator11AddNewValueForPrefCoin(aviator11Coins)
        }
    }

    fun aviator11CheckIndexes(): Int {
        val aviator11Multiply = aviator11Bet*aviator11BetX
        if (aviator11Array[aviator11MachinePos1] == aviator11Array[aviator11MachinePos2]
            && aviator11Array[aviator11MachinePos1] == aviator11Array[aviator11MachinePos3]
        ) {
            return if (aviator11Array[aviator11MachinePos1] == 4) aviator11Multiply*15
            else aviator11Multiply*10
        } else if (aviator11Array[aviator11MachinePos1] == aviator11Array[aviator11MachinePos2]) {
            return aviator11Multiply*3
        }
        return 0
    }


















    fun aviator11ChangeBet(aviator11ChangeBetPlus: Boolean) {
        Log.d("GGG", "Change bet $aviator11ChangeBetPlus")
        when (aviator11ChangeBetPlus) {
            true -> if ((aviator11Bet+5) <= aviator11Coins) aviator11Bet += 5

            false -> if ((aviator11Bet-5) >=0) aviator11Bet -= 5
        }
    }

    fun aviator11ChangeBetX(aviator11ChangeBetX:Boolean) {
        when (aviator11ChangeBetX) {
            true -> if ((aviator11BetX+1) <= 5) aviator11BetX += 1

            false -> if (1 <=(aviator11BetX-1)) aviator11BetX -= 1
        }
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
}