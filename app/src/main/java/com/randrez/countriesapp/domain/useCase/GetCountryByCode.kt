package com.randrez.countriesapp.domain.useCase

import android.content.Context
import com.randrez.countriesapp.R
import com.randrez.countriesapp.data.resource.Result
import com.randrez.countriesapp.domain.mapper.toCountry
import com.randrez.countriesapp.domain.mapper.toListItemCountry
import com.randrez.countriesapp.domain.model.Country
import com.randrez.countriesapp.domain.repository.CountryRepository

class GetCountryByCode(
    private val context:Context,
    private val countryRepository: CountryRepository
) {

    suspend operator fun invoke(code: String): Result<Country> =
        if (code.isNotBlank()) {
            countryRepository.getCountryByCode(code)?.let {
                val countriesEntity =
                    countryRepository.getCountriesByCodes(it.borders)
                if (countriesEntity.isNotEmpty())
                    Result.Success(it.toCountry(countriesEntity.toListItemCountry()))
                else
                    Result.Success(it.toCountry(emptyList()))
            } ?: Result.Error(context.getString(R.string.not_found_country))
        } else
            Result.Error(context.getString(R.string.code_is_empty))
}