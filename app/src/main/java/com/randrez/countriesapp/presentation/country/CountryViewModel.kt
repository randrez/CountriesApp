package com.randrez.countriesapp.presentation.country

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.randrez.countriesapp.data.resource.Result
import com.randrez.countriesapp.domain.useCase.GetCountryByCode
import com.randrez.countriesapp.presentation.navigation.Arguments.CODE
import com.randrez.countriesapp.presentation.navigation.Arguments.IMAGE
import com.randrez.countriesapp.presentation.navigation.Arguments.TITLE
import com.randrez.countriesapp.presentation.navigation.NavigationEvent
import com.randrez.countriesapp.tools.decodeImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getCountryByCode: GetCountryByCode
) : ViewModel() {

    private val _state: MutableState<CountryState> = mutableStateOf(CountryState())
    val state: State<CountryState> = _state
    private val _navigationEventFlow: MutableSharedFlow<NavigationEvent> =
        MutableSharedFlow(replay = 0)
    val navigationEventFlow: SharedFlow<NavigationEvent> = _navigationEventFlow

    init {
        savedStateHandle.keys().forEach { key ->
            val stateValue = state.value
            when (key) {
                CODE -> savedStateHandle.get<String>(key)?.let {
                    _state.value = stateValue.copy(code = it)
                }

                TITLE -> savedStateHandle.get<String>(key)?.let {
                    _state.value = stateValue.copy(title = it)
                }

                IMAGE -> savedStateHandle.get<String>(key)?.let {
                    if (it.isNotBlank() && it != "null") {
                        val decodedUrl = decodeImage(it)
                        _state.value = stateValue.copy(image = decodedUrl)
                    }
                }
            }
        }

        state.value.run {
            getCountryDetail(code)
        }
    }

    private fun getCountryDetail(code: String) {
        viewModelScope.launch {
            when (val result = getCountryByCode.invoke(code)) {
                is Result.Error -> {
                    result.message?.let {
                        _state.value = state.value.copy(message = it, loading = false)
                    }
                }

                is Result.Success -> {
                    _state.value = state.value.copy(country = result.data, loading = false)
                }
            }
        }
    }

    fun onBackStack() {
        viewModelScope.launch {
            _navigationEventFlow.emit(NavigationEvent.OnBackStack)
        }
    }
}