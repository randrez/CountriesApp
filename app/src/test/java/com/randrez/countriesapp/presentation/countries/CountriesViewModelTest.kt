package com.randrez.countriesapp.presentation.countries

import com.randrez.countriesapp.MainCoroutineRule
import com.randrez.countriesapp.base.DataState
import com.randrez.countriesapp.domain.useCase.SearchCountry
import com.randrez.countriesapp.domain.useCase.SetCountries
import com.randrez.countriesapp.mocks.mockListItemCountry
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CountriesViewModelTest {

    @MockK
    private lateinit var setCountries: SetCountries

    @MockK
    private lateinit var searchCountry: SearchCountry

    private lateinit var viewModel: CountriesViewModel

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        coEvery { setCountries.invoke() } returns DataState.Success(true)
        coEvery { searchCountry.invoke() } returns DataState.Success(mockListItemCountry())
        viewModel = CountriesViewModel(setCountries, searchCountry)
    }

    @Test
    fun `when viewmodel is created at the first time, save from database all countries`() =
        runTest {

            //Given
            coEvery { setCountries() } returns DataState.Success(true)

            //When
            viewModel.setCountriesLocal()

            //Then
            val currentState = viewModel.state.value
            assertEquals(false, currentState.loading)
            assertEquals("", currentState.message)
        }

    @Test
    fun `when viewmodel is created at the first time, save fail`() = runTest {

        //Given
        val errorMessage = "Error save countries in database"
        coEvery { setCountries() } returns DataState.Error(errorMessage)

        //When
        viewModel.setCountriesLocal()

        //Then
        val currentState = viewModel.state.value
        assertEquals(false, currentState.loading)
        assertEquals(errorMessage, currentState.message)
    }

    @Test
    fun `when filter countries the query is empty but return all countries`() = runTest {

        //Given
        coEvery { searchCountry.invoke() } returns DataState.Success(mockListItemCountry())

        //When
        viewModel.filterCountries()

        //Then
        val currentState = viewModel.state.value
        assertEquals(false, currentState.loading)
        assert(viewModel.countriesFiltered.size == mockListItemCountry().size)
    }

    @Test
    fun `when filter countries the query is empty but fail return countries`() = runTest {

        //Given
        val message = "Not found countries"
        coEvery { searchCountry.invoke() } returns DataState.Error(message)

        //When
        viewModel.filterCountries()

        //Then
        val currentState = viewModel.state.value
        assertEquals(false, currentState.loading)
        assertEquals(message, currentState.message)
    }

    @Test
    fun `when search countries and return countries or country`() = runTest {

        //Given
        val query = "moz"
        coEvery { searchCountry.invoke(query) } returns DataState.Success(mockListItemCountry())

        //When
        viewModel.onEventUI(CountriesEventUI.OnSearchQueryCountry(query))

        //Then
        assert(viewModel.countriesFiltered.size == mockListItemCountry().size)
    }

    @Test
    fun `when search countries and return empty countries`() = runTest {

        //Given
        val query = "xz"
        val message = "Not found countries"
        coEvery { searchCountry.invoke(query) } returns DataState.Error(message)

        //When
        viewModel.onEventUI(CountriesEventUI.OnSearchQueryCountry(query))

        //Then
        assert(viewModel.countriesFiltered.size == 0)
    }

    @Test
    fun `when navigate to detail country clear list and show all countries`() = runTest {

        //Given
        coEvery { searchCountry.invoke() } returns DataState.Success(mockListItemCountry())

        //When
        viewModel.clearSearch()

        //Then
        assert(viewModel.countriesFiltered.size == mockListItemCountry().size)
    }

    @Test
    fun `when press on back in keyboard or back arrow`() = runTest {

        //Given
        coEvery { searchCountry.invoke() } returns DataState.Success(mockListItemCountry())

        //When
        viewModel.onEventUI(CountriesEventUI.OnBackStack)

        //Then
        assert(viewModel.countriesFiltered.size == mockListItemCountry().size)
    }

}