package com.randrez.countriesapp.domain.useCase

import android.content.Context
import com.randrez.countriesapp.R
import com.randrez.countriesapp.base.DataState
import com.randrez.countriesapp.domain.mapper.toCountry
import com.randrez.countriesapp.domain.mapper.toListItemCountry
import com.randrez.countriesapp.domain.model.Country
import com.randrez.countriesapp.domain.repository.CountryRepository

class GetCountryByCode(
    private val context:Context,
    private val countryRepository: CountryRepository
) {

    suspend operator fun invoke(code: String): DataState<Country> =
        if (code.isNotBlank()) {
            countryRepository.getCountryByCode(code)?.let {
                val countriesEntity =
                    countryRepository.getCountriesByCodes(it.borders)
                if (countriesEntity.isNotEmpty())
                    DataState.Success(it.toCountry(countriesEntity.toListItemCountry()))
                else
                    DataState.Success(it.toCountry(emptyList()))
            } ?: DataState.Error(context.getString(R.string.not_found_country))
        } else
            DataState.Error(context.getString(R.string.code_is_empty))
}