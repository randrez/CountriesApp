package com.randrez.countriesapp.presentation.country

import androidx.lifecycle.SavedStateHandle
import com.randrez.countriesapp.MainCoroutineRule
import com.randrez.countriesapp.base.DataState
import com.randrez.countriesapp.domain.useCase.GetCountryByCode
import com.randrez.countriesapp.mocks.mockCountry
import com.randrez.countriesapp.mocks.mockListItemCountry
import com.randrez.countriesapp.presentation.navigation.Arguments
import com.randrez.countriesapp.presentation.navigation.NavigationEvent
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CountryViewModelTest {

    @MockK
    private lateinit var getCountryByCode: GetCountryByCode

    @MockK
    private lateinit var savedStateHandle: SavedStateHandle

    private lateinit var viewModel: CountryViewModel

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        coEvery { getCountryByCode.invoke("MOZ") } returns DataState.Success(
            mockCountry(
                mockListItemCountry()
            )
        )
        savedStateHandle = SavedStateHandle(
            mapOf(
                Arguments.CODE to "MOZ",
                Arguments.TITLE to "Mozambique",
                Arguments.IMAGE to "imageString"
            )
        )
        viewModel = CountryViewModel(savedStateHandle, getCountryByCode)
    }

    @Test
    fun `when state is set from savedStateHandle`() = runTest {
        val state = viewModel.state.value

        coVerify(exactly = 1) { getCountryByCode.invoke(state.code) }
        assertEquals("MOZ", state.code)
        assertEquals("Mozambique", state.title)
        assertEquals("imageString", state.image)
    }

    @Test
    fun `when get country detail by code and return country`() = runTest {
        val code = "MOZ"
        val country = mockCountry(mockListItemCountry())
        coEvery { getCountryByCode.invoke(code) } returns DataState.Success(country)

        viewModel.getCountryDetail(code)
        val state = viewModel.state.value
        assertEquals(country, state.country)
        assertEquals(false, state.loading)
    }

    @Test
    fun `when get country detail by code and return empty`() = runTest {
        val code = "TEST"
        coEvery { getCountryByCode.invoke(code) } returns DataState.Error("Not found country")

        viewModel.getCountryDetail(code)

        advanceUntilIdle()
        val state = viewModel.state.value
        assertEquals("Not found country", state.message)
        assertEquals(false, state.loading)
    }

    @Test
    fun`when press back button arrow or press back in device`() = runTest {
        viewModel.onBackStack()

        val event = NavigationEvent.OnBackStack
        assertTrue(event is NavigationEvent.OnBackStack)
    }
}