package com.randrez.countriesapp.domain.useCase

import android.content.Context
import com.randrez.countriesapp.data.resource.Result
import com.randrez.countriesapp.domain.repository.CountryRepository
import com.randrez.countriesapp.mocks.mockCountryEntity
import com.randrez.countriesapp.mocks.mockListCountryEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCountryByCodeTest {

    @MockK
    private lateinit var countryRepository: CountryRepository

    @MockK
    private lateinit var context: Context

    private lateinit var getCountryByCode: GetCountryByCode

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getCountryByCode =
            GetCountryByCode(context = context, countryRepository = countryRepository)
    }

    @Test
    fun `when code is no empty then return country and borders from database`() =
        runBlocking {
            val code = "MOZ"
            //Given
            coEvery { countryRepository.getCountryByCode(code) } returns mockCountryEntity()
            coEvery { countryRepository.getCountriesByCodes(mockCountryEntity().borders) } returns mockListCountryEntity()

            //When
            val response = getCountryByCode(code)

            //Then
            assert(response is Result.Success)
        }

    @Test
    fun `when code is empty then return message error`() =
        runBlocking {
            //Given
            coEvery { countryRepository.getCountryByCode("") } returns null
            every { context.getString(any()) } returns "No enter code"

            //When
            val response = getCountryByCode("")

            //Then
            assert(response is Result.Error)
        }

    @Test
    fun `when code is no empty then return country but borders is empty`() =
        runBlocking {
            //Given
            val code = "MOZ"
            coEvery { countryRepository.getCountryByCode(code) } returns mockCountryEntity()
            coEvery { countryRepository.getCountriesByCodes(mockCountryEntity().borders) } returns emptyList()

            //When
            val response = getCountryByCode(code)

            //Then
            assert(response is Result.Success)
        }


    @Test
    fun `when code is no empty then database return anything`() =
        runBlocking {
            //Given
            val code = "MAZ"
            coEvery { countryRepository.getCountryByCode(code) } returns null
            every { context.getString(any()) } returns "Not found country"

            //When
            val response = getCountryByCode(code)

            //Then
            assert(response is Result.Error)
        }
}