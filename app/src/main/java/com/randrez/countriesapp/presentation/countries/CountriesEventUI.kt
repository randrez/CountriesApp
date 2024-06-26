package com.randrez.countriesapp.presentation.countries

import com.randrez.countriesapp.domain.model.ItemCountry

sealed class CountriesEventUI {
    data class OnSelectCountry(val itemCountry: ItemCountry) : CountriesEventUI()
    object OnBackStack : CountriesEventUI()
    data class OnSearchQueryCountry(val value: String) : CountriesEventUI()
    object OnClearSearchQueryCountry : CountriesEventUI()
}