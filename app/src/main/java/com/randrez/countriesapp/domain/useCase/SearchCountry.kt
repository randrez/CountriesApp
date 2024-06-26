package com.randrez.countriesapp.domain.useCase

import android.content.Context
import com.randrez.countriesapp.R
import com.randrez.countriesapp.base.DataState
import com.randrez.countriesapp.domain.mapper.toListItemCountry
import com.randrez.countriesapp.domain.model.ItemCountry
import com.randrez.countriesapp.domain.repository.CountryRepository
import com.randrez.database.entity.CountryEntity

class SearchCountry(
    private val context: Context,
    private val countryRepository: CountryRepository
) {
    suspend operator fun invoke(query: String = ""): DataState<List<ItemCountry>> {
        var countryEntityList: List<CountryEntity>
        if (query.isBlank()) {
            countryEntityList = countryRepository.getCountries()
            if (countryEntityList.isEmpty()) {
                return DataState.Error(context.getString(R.string.not_found_countries))
            }
        } else {
            countryEntityList = countryRepository.searchCountry(query)
            if (countryEntityList.isEmpty()) {
                return DataState.Error(context.getString(R.string.not_found_countries))
            }
        }

        return DataState.Success(countryEntityList.toListItemCountry())
    }
}