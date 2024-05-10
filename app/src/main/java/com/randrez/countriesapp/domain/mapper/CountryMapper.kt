package com.randrez.countriesapp.domain.mapper

import com.randrez.countriesapp.domain.model.Country
import com.randrez.countriesapp.domain.model.ItemCountry
import com.randrez.database.entity.CountryEntity
import com.randrez.network.response.CountryDTO

fun List<CountryDTO>.toListCountryEntity() =
    this.map { it.toCountryEntity() }

fun CountryDTO.toCountryEntity() =
    CountryEntity(
        code = this.code,
        name = this.namesDTO.common,
        officialName = this.namesDTO.official,
        region = this.region,
        subregion = this.subregion,
        population = this.population.toString(),
        capital = this.capital?.first(),
        borders = this.borders ?: emptyList(),
        latitude = this.latitudeLongitude[0],
        longitude = this.latitudeLongitude[1],
        image = this.flagDTO.png
    )

fun List<CountryEntity>.toListItemCountry() =
    this.map { it.toItemCountry() }

fun CountryEntity.toItemCountry() =
    ItemCountry(
        code = this.code,
        name = this.name ?: "",
        official = this.officialName ?: "",
        capital = this.capital ?: "",
        image = this.image
    )

fun CountryEntity.toCountry(itemsCountry: List<ItemCountry>) =
    Country(
        code = this.code,
        name = this.name ?: "No name",
        officialName = this.officialName ?: "No official name",
        region = this.region ?: "No region",
        population = this.population ?: "0",
        capital = this.capital ?: "No capital",
        borders = itemsCountry
    )

