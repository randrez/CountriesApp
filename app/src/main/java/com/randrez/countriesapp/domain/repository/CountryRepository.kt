package com.randrez.countriesapp.domain.repository

import com.randrez.database.entity.CountryEntity
import com.randrez.network.response.CountryDTO

interface CountryRepository {
    suspend fun getAllCountryApi(): List<CountryDTO>
    suspend fun countCountries(): Int
    suspend fun insertList(countries: List<CountryEntity>): List<Long>
    suspend fun searchCountry(query: String): List<CountryEntity>
    suspend fun getCountries(): List<CountryEntity>
    suspend fun getCountryByCode(code: String): CountryEntity?
    suspend fun getCountriesByCodes(codes: List<String>): List<CountryEntity>
}