package com.randrez.countriesapp.domain.useCase

import android.content.Context
import com.randrez.countriesapp.R
import com.randrez.countriesapp.data.resource.Result
import com.randrez.countriesapp.domain.mapper.toListCountryEntity
import com.randrez.countriesapp.domain.repository.CountryRepository

class SetCountries(
    private val context: Context,
    private val countryRepository: CountryRepository
) {

    suspend operator fun invoke(): Result<Boolean> {
        val count = countryRepository.countCountries()
        if (count == 0) {
            val countriesDTO = countryRepository.getAllCountryApi()
            if (countriesDTO.isEmpty()) {
                return Result.Error(context.getString(R.string.not_found_countries_service))
            }else{
                val success = countryRepository.insertList(countriesDTO.toListCountryEntity())
                if (success.contains(0)) {
                    return Result.Error(context.getString(R.string.error_save_countries_data_base))
                }
                return Result.Success(success.isNotEmpty())
            }
        } else {
            return Result.Success(count > 0)
        }
    }
}