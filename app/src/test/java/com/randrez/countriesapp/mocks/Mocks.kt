package com.randrez.countriesapp.mocks

import com.randrez.countriesapp.domain.mapper.toCountryEntity
import com.randrez.database.entity.CountryEntity
import com.randrez.network.response.CountryDTO
import com.randrez.network.response.FlagDTO
import com.randrez.network.response.NamesDTO

fun mockCountryDTO() =
    CountryDTO(
        borders = listOf("MOZ"),
        capital = listOf("Maputo"),
        code = "MOZ",
        flagDTO = mockFlag(),
        latitudeLongitude = listOf(-18.0, 35.0),
        namesDTO = mockNamesDTO(),
        population = 31255435,
        region = "Africa",
        subregion = "Eastern Africa"
    )


fun mockListCountries(): List<CountryDTO> =
    listOf(mockCountryDTO())

fun mockFlag(): FlagDTO =
    FlagDTO(alt = "", png = "https://mainfacts.com/media/images/coats_of_arms/mz.png", svg = "")

fun mockNamesDTO(): NamesDTO =
    NamesDTO(common = "Mozambique", official = "Republic of Mozambique")

fun mockListCountryEntity(): List<CountryEntity> =
    mockListCountries().map { it.toCountryEntity() }

fun mockCountryEntity(): CountryEntity =
    mockCountryDTO().toCountryEntity()
