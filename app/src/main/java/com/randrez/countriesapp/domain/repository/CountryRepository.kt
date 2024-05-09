package com.randrez.countriesapp.domain.repository

import com.randrez.network.response.CountryDTO

interface CountryRepository {
    suspend fun getAllCountryApi():List<CountryDTO>
}