package com.randrez.countriesapp.domain.useCase

import com.randrez.countriesapp.data.resource.Result
import com.randrez.countriesapp.domain.mapper.toListItemCountry
import com.randrez.countriesapp.domain.model.ItemCountry
import com.randrez.countriesapp.domain.repository.CountryRepository


class GetCountriesUseCase(
    private val countryRepository: CountryRepository
) {

    suspend operator fun invoke(): Result<List<ItemCountry>> {
        val countriesDTO = countryRepository.getAllCountryApi()
        return if (countriesDTO.isEmpty()) {
            Result.Error("No se encontraron Paises")
        } else {
            Result.Success(countriesDTO.toListItemCountry())
        }
    }
}