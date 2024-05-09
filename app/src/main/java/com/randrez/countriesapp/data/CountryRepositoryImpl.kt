package com.randrez.countriesapp.data

import com.randrez.countriesapp.domain.repository.CountryRepository
import com.randrez.network.ApiService
import com.randrez.network.response.CountryDTO

class CountryRepositoryImpl(
    private val apiService: ApiService
):CountryRepository {
    override suspend fun getAllCountryApi(): List<CountryDTO>  =
        apiService.getAllCountries()
}