package com.randrez.countriesapp.di

import android.content.Context
import androidx.room.Room
import com.randrez.database.AppDataBase
import com.randrez.database.dao.CountryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext context: Context
    ): AppDataBase =
        Room.databaseBuilder(
            context = context,
            klass = AppDataBase::class.java,
            name = "countries_db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideCountryDao(dataBase: AppDataBase): CountryDao =
        dataBase.countryDao()
}