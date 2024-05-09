package com.randrez.countriesapp.presentation.country

import androidx.compose.runtime.mutableStateListOf
import com.randrez.countriesapp.domain.model.Country
import com.randrez.countriesapp.domain.model.ItemCountry

data class CountryState(
    val country: Country? = null,
    val loading:Boolean = true,
    val borders:MutableList<ItemCountry> = mutableStateListOf(),
    val title:String = "",
    val image:String = "",
    val code:String = ""
)