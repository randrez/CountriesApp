package com.randrez.countriesapp.presentation.navigation

import com.randrez.countriesapp.presentation.navigation.ConstantsRoute.COUNTRIES
import com.randrez.countriesapp.presentation.navigation.ConstantsRoute.COUNTRY

sealed class Routes(
    val route:String
)  {
    object Countries:Routes(
        route = COUNTRIES
    )
    object Country :Routes(
        route = COUNTRY
    )
}