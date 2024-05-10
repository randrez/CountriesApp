package com.randrez.countriesapp.di

import com.randrez.countriesapp.data.CountryRepositoryImpl
import com.randrez.countriesapp.domain.repository.CountryRepository
import com.randrez.database.dao.CountryDao
import com.randrez.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCountryRepository(
        apiService: ApiService,
        countryDao: CountryDao
    ): CountryRepository =
        CountryRepositoryImpl(apiService = apiService, countryDao = countryDao)
}