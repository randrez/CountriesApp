package com.randrez.countriesapp.data

import com.randrez.countriesapp.domain.repository.CountryRepository
import com.randrez.database.dao.CountryDao
import com.randrez.database.entity.CountryEntity
import com.randrez.network.ApiService
import com.randrez.network.response.CountryDTO

class CountryRepositoryImpl(
    private val apiService: ApiService,
    private val countryDao: CountryDao
) : CountryRepository {
    override suspend fun getAllCountryApi(): List<CountryDTO> =
        apiService.getAllCountries()

    override suspend fun countCountries(): Int =
        countryDao.count()

    override suspend fun insertList(countries: List<CountryEntity>): List<Long> =
        countryDao.insertAll(countries)

    override suspend fun searchCountry(query: String): List<CountryEntity> =
        countryDao.searchCountryByQuery(query = query)

    override suspend fun getCountries(): List<CountryEntity> =
        countryDao.getCountries()

    override suspend fun getCountryByCode(code: String): CountryEntity? =
        countryDao.getCountryByCode(code)

    override suspend fun getCountriesByCodes(codes: List<String>): List<CountryEntity> =
        countryDao.getCountriesByCodes(codes = codes)
}