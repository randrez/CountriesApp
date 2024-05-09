package com.randrez.countriesapp.presentation.countries

import com.randrez.countriesapp.domain.model.ItemCountry

data class CountriesState(
    val loading: Boolean = false,
    val itemCountrySelected: ItemCountry? = null,
    val searchQueryCountry: String = ""
)