package com.randrez.countriesapp.presentation.country

import com.randrez.countriesapp.domain.model.Country

data class CountryState(
    val country: Country? = null,
    val loading: Boolean = true,
    val title: String = "",
    val image: String = "",
    val code: String = ""
)