package com.randrez.countriesapp.domain.useCase

import android.content.Context
import com.randrez.countriesapp.R
import com.randrez.countriesapp.base.DataState
import com.randrez.countriesapp.domain.mapper.toListCountryEntity
import com.randrez.countriesapp.domain.repository.CountryRepository

class SetCountries(
    private val context: Context,
    private val countryRepository: CountryRepository
) {

    suspend operator fun invoke(): DataState<Boolean> {
        val count = countryRepository.countCountries()
        if (count == 0) {
            val countriesDTO = countryRepository.getAllCountryApi()
            if (countriesDTO.isEmpty()) {
                return DataState.Error(context.getString(R.string.not_found_countries_service))
            } else {
                val success = countryRepository.insertList(countriesDTO.toListCountryEntity())
                if (success.contains(0)) {
                    return DataState.Error(context.getString(R.string.error_save_countries_data_base))
                }
            }
            return DataState.Success(true)
        } else {
            return DataState.Success(true)
        }
    }
}