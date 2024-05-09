package com.randrez.countriesapp.di

import com.randrez.countriesapp.domain.repository.CountryRepository
import com.randrez.countriesapp.domain.useCase.GetCountriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    fun provideGetCountriesUseCase(countryRepository: CountryRepository): GetCountriesUseCase =
        GetCountriesUseCase(countryRepository = countryRepository)
}
