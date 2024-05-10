package com.randrez.countriesapp.presentation.countries

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.randrez.countriesapp.data.resource.Result
import com.randrez.countriesapp.domain.model.ItemCountry
import com.randrez.countriesapp.domain.useCase.SearchCountry
import com.randrez.countriesapp.domain.useCase.SetCountries
import com.randrez.countriesapp.presentation.navigation.NavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val setCountries: SetCountries,
    private val searchCountry: SearchCountry
) : ViewModel() {

    private val _state: MutableState<CountriesState> = mutableStateOf(CountriesState())
    val state: State<CountriesState> = _state
    val countriesFiltered: MutableList<ItemCountry> = mutableStateListOf()

    private val _navigationEventFlow: MutableSharedFlow<NavigationEvent> =
        MutableSharedFlow(replay = 0)
    val navigationEventFlow: SharedFlow<NavigationEvent> = _navigationEventFlow

    init {
        setCountriesLocal()
    }

    fun setCountriesLocal() {
        viewModelScope.launch {
            when (val result = setCountries.invoke()) {
                is Result.Error -> {
                    result.message?.let {
                        _state.value = state.value.copy(loading = false, message = it)
                    }
                }

                is Result.Success -> {
                    filterCountries()
                }
            }
        }
    }

    fun onEventUI(eventUI: CountriesEventUI) {
        when (eventUI) {
            is CountriesEventUI.OnSelectCountry -> {
                viewModelScope.launch {
                    _navigationEventFlow.emit(NavigationEvent.OnNavigateCountry(eventUI.itemCountry))
                }
            }

            is CountriesEventUI.OnBackStack -> {
                viewModelScope.launch {
                    _navigationEventFlow.emit(NavigationEvent.OnBackStack)
                }
            }

            is CountriesEventUI.OnSearchQueryCountry -> {
                _state.value = state.value.copy(searchCountry = eventUI.value, loading = true)
                viewModelScope.launch {
                    filterCountries()
                }
            }

            is CountriesEventUI.OnClearSearchQueryCountry -> {
                clearSearch()
            }
        }
    }

    fun clearSearch() {
        _state.value = state.value.copy(searchCountry = "", loading = true)
        viewModelScope.launch {
            filterCountries()
        }
    }

    suspend fun filterCountries() {
        countriesFiltered.clear()
        when (val result = searchCountry.invoke(state.value.searchCountry)) {
            is Result.Error -> {
                result.message?.let {
                    _state.value = state.value.copy(loading = false, message = it)
                }
            }

            is Result.Success -> {
                _state.value = state.value.copy(loading = false)
                result.data?.let {
                    countriesFiltered.addAll(it)
                }
            }
        }
    }
}