package com.crashaviatorjogo.slod.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.crashaviatorjogo.slod.domain.usecases.CollectDataUseCase
import com.crashaviatorjogo.slod.domain.usecases.FetchSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class StartViewModel @Inject constructor(
    private val fetchSettingsUseCase: FetchSettingsUseCase,
    private val collectDataUseCase: CollectDataUseCase
) : ViewModel() {

    private val _path = MutableStateFlow("")
    private val _data get() = collectDataUseCase()

    val data: Flow<String> get() = _data.transform { data ->
        _path.value = data

        emit(data)
    }
    val path get() = _path.value

    fun fetchSettings() = fetchSettingsUseCase()
}