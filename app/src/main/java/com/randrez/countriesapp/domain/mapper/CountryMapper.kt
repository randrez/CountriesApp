package com.randrez.countriesapp.domain.mapper

import com.randrez.countriesapp.domain.model.ItemCountry
import com.randrez.network.response.CountryDTO

fun List<CountryDTO>.toListItemCountry(): List<ItemCountry> =
    this.map { it.toItemCountry() }

fun CountryDTO.toItemCountry() =
    ItemCountry(
        code = this.code,
        official = this.namesDTO.official,
        name = this.namesDTO.common,
        capital = this.capital?.first() ?: "",
        image = this.flagDTO.png
    )
