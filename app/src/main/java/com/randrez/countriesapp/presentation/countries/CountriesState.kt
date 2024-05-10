package com.randrez.countriesapp.presentation.countries

import com.randrez.countriesapp.domain.model.ItemCountry

data class CountriesState(
    val loading: Boolean = true,
    val itemCountrySelected: ItemCountry? = null,
    val searchCountry: String = "",
    val message: String = ""
)