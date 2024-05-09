package com.randrez.countriesapp.presentation.countries

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.randrez.countriesapp.domain.model.ItemCountry
import com.randrez.countriesapp.presentation.navigation.NavigationEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(

) : ViewModel() {

    private val _state: MutableState<CountriesState> = mutableStateOf(CountriesState())
    val state: State<CountriesState> = _state
    val countriesFiltered: MutableList<ItemCountry> = mutableStateListOf()
    val countries: MutableList<ItemCountry> = mutableStateListOf()

    protected val _navigationEventFlow: MutableSharedFlow<NavigationEvent> =
        MutableSharedFlow(replay = 0)
    val navigationEventFlow: SharedFlow<NavigationEvent> = _navigationEventFlow

    init {
        countriesFiltered.add(
            ItemCountry(
                code = "MDA",
                name = "Republic of Moldova",
                capital = "Chișinău",
                image = "https://flagcdn.com/w320/md.png"
            )
        )
        countriesFiltered.add(
            ItemCountry(
                code = "MDA",
                name = "Republic of Moldova",
                capital = "Chișinău",
                image = "https://flagcdn.com/w320/md.png"
            )
        )
        countriesFiltered.add(
            ItemCountry(
                code = "MDA",
                name = "Republic of Moldova",
                capital = "Chișinău",
                image = "https://flagcdn.com/w320/md.png"
            )
        )
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
        }
    }
}