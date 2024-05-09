package com.randrez.countriesapp.presentation.country

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.randrez.countriesapp.domain.model.Country
import com.randrez.countriesapp.domain.model.ItemCountry
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
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state: MutableState<CountryState> = mutableStateOf(CountryState())
    val state: State<CountryState> = _state
    protected val _navigationEventFlow: MutableSharedFlow<NavigationEvent> =
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
            if (code.isNotBlank())
                getCountryDetail(code)
        }
    }

    private fun getCountryDetail(code: String) {
        val countryMock = Country(
            code = code,
            name = "Moldova",
            officialName = "Republic of Moldova",
            capital = "Chișinău",
            region = "Europe",
            population = "2617820",
            borders = listOf(
                "ROU",
                "UKR"
            )
        )

        getCountryBorders(country = countryMock)
    }

    private fun getCountryBorders(country: Country) {
        val borders = listOf(
            ItemCountry(code = "ROU", name = "Romania", capital = "Bucharest"),
            ItemCountry(code = "UKR", name = "Ukraine", capital = "Kyiv"),
            ItemCountry(code = "UKR", name = "Ukraine", capital = "Kyiv"),
            ItemCountry(code = "UKR", name = "Ukraine", capital = "Kyiv"),
            ItemCountry(code = "UKR", name = "Ukraine", capital = "Kyiv"),
            ItemCountry(code = "UKR", name = "Ukraine", capital = "Kyiv")
        )
        _state.value =
            state.value.copy(country = country, borders = borders.toMutableList(), loading = false)
    }

    fun onBackStack() {
        viewModelScope.launch {
            _navigationEventFlow.emit(NavigationEvent.OnBackStack)
        }
    }
}