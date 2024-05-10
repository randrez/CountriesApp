package com.randrez.countriesapp.domain.useCase

import android.content.Context
import com.randrez.countriesapp.data.resource.Result
import com.randrez.countriesapp.domain.repository.CountryRepository
import com.randrez.countriesapp.mocks.mockListCountries
import com.randrez.countriesapp.mocks.mockListCountryEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SetCountriesTest {

    @MockK
    private lateinit var countryRepository: CountryRepository

    @MockK
    private lateinit var context: Context

    private lateinit var setCountries: SetCountries

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        setCountries = SetCountries(context = context, countryRepository = countryRepository)
    }

        @Test
        fun `when database doesn't return anything then consume api and save countries in database`() =
            runBlocking {
            //Given
            val successSave: List<Long> = listOf(1)
            coEvery { countryRepository.countCountries() } returns 0
            coEvery { countryRepository.getAllCountryApi() } returns mockListCountries()
            coEvery { countryRepository.insertList(mockListCountryEntity()) } returns successSave

            //When
            val response = setCountries()

            //Then
            coVerify(exactly = 1) { countryRepository.getAllCountryApi() }
            coVerify(exactly = 1) { countryRepository.insertList(mockListCountryEntity()) }
            assert(response is Result.Success)
        }

    @Test
    fun `when database doesn't return anything then consume api and fail save in database`() =
        runBlocking {
            //Given
            val failSave: List<Long> = listOf(0)
            coEvery { countryRepository.countCountries() } returns 0
            coEvery { countryRepository.getAllCountryApi() } returns mockListCountries()
            coEvery { countryRepository.insertList(mockListCountryEntity()) } returns failSave

            every { context.getString(any()) } returns "Fail save in database"

            //When
            val response = setCountries()

            //Then
            coVerify(exactly = 1) { countryRepository.getAllCountryApi() }
            coVerify(exactly = 1) { countryRepository.insertList(mockListCountryEntity()) }
            assert(response is Result.Error)
        }

    @Test
    fun `when database doesn't return anything then consume api return anything`() =
        runBlocking {
            //Given
            coEvery { countryRepository.countCountries() } returns 0
            coEvery { countryRepository.getAllCountryApi() } returns emptyList()

            every { context.getString(any()) } returns "Service no return countries"

            //When
            val response = setCountries()

            //Then
            coVerify(exactly = 1) { countryRepository.getAllCountryApi() }
            assert(response is Result.Error)
        }

    @Test
    fun `when database return  size countries`() =
        runBlocking {
            //Given
            coEvery { countryRepository.countCountries() } returns 1

            //When
            val response = setCountries()

            //Then
            assert(response is Result.Success)
        }
}