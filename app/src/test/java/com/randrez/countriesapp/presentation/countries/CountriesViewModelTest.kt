

package com.randrez.countriesapp.presentation.countries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.randrez.countriesapp.data.resource.Result
import com.randrez.countriesapp.domain.useCase.SearchCountry
import com.randrez.countriesapp.domain.useCase.SetCountries
import com.randrez.countriesapp.mocks.mockListItemCountry
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CountriesViewModelTest {

    @MockK
    private lateinit var setCountries: SetCountries

    @MockK
    private lateinit var searchCountry: SearchCountry

    private lateinit var countriesViewModel: CountriesViewModel


    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        countriesViewModel = CountriesViewModel(setCountries, searchCountry)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, save from database all countries`() =
        runTest {

        }
}