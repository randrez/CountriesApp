package com.randrez.countriesapp.presentation.navigation

import com.randrez.countriesapp.domain.model.ItemCountry

sealed class NavigationEvent {
    data class OnNavigateCountry(val itemCountry: ItemCountry) : NavigationEvent()
    object OnBackStack : NavigationEvent()
}