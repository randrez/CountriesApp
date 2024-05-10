package com.randrez.countriesapp.presentation.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.randrez.countriesapp.presentation.countries.CountriesScreen
import com.randrez.countriesapp.presentation.countries.CountriesViewModel
import com.randrez.countriesapp.presentation.country.CountryScreen
import com.randrez.countriesapp.presentation.country.CountryViewModel
import com.randrez.countriesapp.presentation.navigation.NavigationEvent.OnBackStack
import com.randrez.countriesapp.presentation.navigation.NavigationEvent.OnNavigateCountry
import com.randrez.countriesapp.presentation.navigation.Routes.Countries
import com.randrez.countriesapp.presentation.navigation.Routes.Country
import com.randrez.countriesapp.tools.encodeImage

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Countries.route) {
        composable(route = Countries.route) {
            val viewModel: CountriesViewModel = hiltViewModel()
            val state = viewModel.state.value
            val activity = (LocalContext.current as? Activity)
            LaunchedEffect(Unit) {
                viewModel.navigationEventFlow.collect { event ->
                    when (event) {
                        is OnBackStack -> {
                            activity?.finishAffinity()
                        }

                        is OnNavigateCountry -> {
                            event.itemCountry.image?.let { image ->
                                val encodedUrl = encodeImage(image)
                                navController.navigate(route = "${Country.route}/${event.itemCountry.code}/${event.itemCountry.name}/${encodedUrl}")
                                if (state.searchCountry.isNotBlank())
                                    viewModel.clearSearch()
                            }
                        }
                    }
                }
            }
            CountriesScreen(
                state = state,
                countries = viewModel.countriesFiltered,
                onEventUI = viewModel::onEventUI
            )
        }

        composable(
            route = "${Country.route}/{${Arguments.CODE}}/{${Arguments.TITLE}}/{${Arguments.IMAGE}}",
            arguments = listOf(
                navArgument(Arguments.CODE) {
                    type = NavType.StringType
                },
                navArgument(Arguments.TITLE) {
                    type = NavType.StringType
                },
                navArgument(Arguments.IMAGE) {
                    type = NavType.StringType
                }
            )
        ) {
            val viewModel: CountryViewModel = hiltViewModel()
            LaunchedEffect(Unit) {
                viewModel.navigationEventFlow.collect {
                    when (it) {
                        is OnBackStack -> navController.popBackStack()
                        else -> {}
                    }
                }
            }
            CountryScreen(
                state = viewModel.state.value,
                obBackStack = viewModel::onBackStack
            )
        }
    }
}


