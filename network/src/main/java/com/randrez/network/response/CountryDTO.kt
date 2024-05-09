package com.randrez.network.response

data class CountryDTO(
    val borders: List<String>,
    val capital: List<String>,
    val cca3: String,
    val continents: List<String>,
    val flagDTO: FlagDTO,
    val latlng: List<Int>,
    val nameDTO: NamesDTO,
    val population: Int,
    val region: String,
    val subregion: String
)