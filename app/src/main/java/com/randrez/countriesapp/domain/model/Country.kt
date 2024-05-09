package com.randrez.countriesapp.domain.model

data class Country(
    val code: String,
    val name: String,
    val officialName: String,
    val region: String,
    val population:String,
    val capital: String,
    val borders: List<String>
)