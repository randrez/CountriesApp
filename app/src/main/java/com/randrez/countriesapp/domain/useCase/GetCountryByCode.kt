package com.randrez.countriesapp.domain.useCase

import com.randrez.countriesapp.domain.mapper.toCountry
import com.randrez.countriesapp.domain.mapper.toListItemCountry
import com.randrez.countriesapp.domain.repository.CountryRepository

class GetCountryByCode(
    private val countryRepository: CountryRepository
) {

    suspend operator fun invoke(code: String) =
        countryRepository.getCountryByCode(code)?.let {
            val itemsCountry = countryRepository.getCountriesByCodes(it.borders).toListItemCountry()
            it.toCountry(itemsCountry)
        }
}