package com.randrez.countriesapp.domain.model

data class ItemCountry(
    val code: String,
    val official: String,
    val name: String,
    val capital: String,
    val image: String? = null
)