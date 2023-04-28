package com.crashaviatorjogo.slod.view.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crashaviatorjogo.slod.R
import com.crashaviatorjogo.slod.data.room.Aviator11DBHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Aviator11MenuViewModel @Inject constructor(
    private val dbHelper: Aviator11DBHelper
): ViewModel() {

    var aviator11QuitScreenVisibility by mutableStateOf(false)
    var aviator11Background by mutableStateOf(R.drawable.background02)

    init {
        viewModelScope.launch {
            aviator11InitDatabase()
        }
    }

    private fun aviator11InitDatabase() {
        viewModelScope.launch {
            dbHelper.aviator11InitDatabase()
        }
    }

    fun aviator11GetBackground() {
        viewModelScope.launch {
            dbHelper.aviator11SelectCurrentPreference("background").collect {
                aviator11Background = it.aviator11PrefValue
            }
        }
    }
}