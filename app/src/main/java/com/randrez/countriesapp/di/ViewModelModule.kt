package com.randrez.countriesapp.di

import android.content.Context
import com.randrez.countriesapp.domain.repository.CountryRepository
import com.randrez.countriesapp.domain.useCase.GetCountryByCode
import com.randrez.countriesapp.domain.useCase.SearchCountry
import com.randrez.countriesapp.domain.useCase.SetCountries
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext


@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideSetCountries(
        @ApplicationContext context: Context,
        countryRepository: CountryRepository
    ) = SetCountries(context = context, countryRepository = countryRepository)

    @Provides
    fun provideSearchCountry(
        @ApplicationContext context: Context,
        countryRepository: CountryRepository
    ) = SearchCountry(context = context, countryRepository = countryRepository)

    @Provides
    fun provideGetCountryByCode(
        @ApplicationContext context: Context,
        countryRepository: CountryRepository
    ) = GetCountryByCode(context = context, countryRepository = countryRepository)
}
