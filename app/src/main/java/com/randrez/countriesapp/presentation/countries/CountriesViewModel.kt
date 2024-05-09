package com.randrez.countriesapp.presentation.countries

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.randrez.countriesapp.data.resource.Result
import com.randrez.countriesapp.domain.model.ItemCountry
import com.randrez.countriesapp.domain.useCase.GetCountriesUseCase
import com.randrez.countriesapp.presentation.navigation.NavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countriesUseCase: GetCountriesUseCase
) : ViewModel() {

    private val _state: MutableState<CountriesState> = mutableStateOf(CountriesState())
    val state: State<CountriesState> = _state
    val countriesFiltered: MutableList<ItemCountry> = mutableStateListOf()
    val countries: MutableList<ItemCountry> = mutableStateListOf()

    protected val _navigationEventFlow: MutableSharedFlow<NavigationEvent> =
        MutableSharedFlow(replay = 0)
    val navigationEventFlow: SharedFlow<NavigationEvent> = _navigationEventFlow

    init {
        viewModelScope.launch {
            getCountries()
        }
    }

    private suspend fun getCountries() {
        countries.clear()
        countriesFiltered.clear()
        when (val result = countriesUseCase.invoke()) {
            is Result.Error -> {}
            is Result.Success -> {
                result.data?.let { itemCountryList ->
                    _state.value = state.value.copy(loading = false)
                    countries.addAll(itemCountryList)
                    countriesFiltered.addAll(itemCountryList)
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
                val filters = countries.filter {
                    it.official.lowercase().contains(eventUI.value) || it.name.lowercase()
                        .contains(eventUI.value)
                }
                countriesFiltered.clear()
                if (filters.isNotEmpty())
                    countriesFiltered.addAll(filters)
                else
                    countriesFiltered.addAll(countries)

                _state.value = state.value.copy(searchQueryCountry = eventUI.value)
            }

            is CountriesEventUI.OnClearSearchQueryCountry -> {
                clearSearch()
            }
        }
    }

    fun clearSearch() {
        _state.value = state.value.copy(searchQueryCountry = "")
        countriesFiltered.clear()
        countriesFiltered.addAll(countries)
    }
}