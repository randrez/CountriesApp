package com.randrez.countriesapp.domain.useCase

import android.content.Context
import com.randrez.countriesapp.data.resource.Result
import com.randrez.countriesapp.domain.repository.CountryRepository
import com.randrez.countriesapp.mocks.mockListCountryEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SearchCountryTest {

    @MockK
    private lateinit var countryRepository: CountryRepository

    @MockK
    private lateinit var context: Context

    private lateinit var searchCountry: SearchCountry

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        searchCountry = SearchCountry(context = context, countryRepository = countryRepository)
    }

    @Test
    fun `when search query is empty then return countries from database`() = runBlocking {
        //Given
        coEvery { countryRepository.getCountries() } returns mockListCountryEntity()

        //When
        val response = searchCountry("")

        //Then
        assert(response is Result.Success)
    }

    @Test
    fun `when search query is empty then return anything from database`() = runBlocking {
        //Given
        coEvery { countryRepository.getCountries() } returns emptyList()
        every { context.getString(any()) } returns "Countries not found"

        //When
        val response = searchCountry("")

        //Then
        assert(response is Result.Error)
    }

    @Test
    fun `when search query is no empty then return countries from database`() = runBlocking {
        //Given
        val query = "Moza"
        coEvery { countryRepository.searchCountry(query) } returns mockListCountryEntity()

        //When
        val response = searchCountry(query)

        //Then
        assert(response is Result.Success)
    }

    @Test
    fun `when search query is no empty then return anything from database`() = runBlocking {
        //Given
        val query = "test"
        coEvery { countryRepository.searchCountry(query) } returns emptyList()
        every { context.getString(any()) } returns "Countries not found"

        //When
        val response = searchCountry(query)

        //Then
        assert(response is Result.Error)
    }
}