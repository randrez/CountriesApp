package com.randrez.countriesapp.presentation.countries

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.randrez.countriesapp.R
import com.randrez.countriesapp.domain.model.ItemCountry
import com.randrez.countriesapp.presentation.commons.CardItemCountry
import com.randrez.countriesapp.presentation.commons.Warning
import com.randrez.countriesapp.presentation.countries.CountriesEventUI.OnBackStack
import com.randrez.countriesapp.presentation.countries.CountriesEventUI.OnClearSearchQueryCountry
import com.randrez.countriesapp.presentation.countries.CountriesEventUI.OnSearchQueryCountry
import com.randrez.countriesapp.presentation.countries.CountriesEventUI.OnSelectCountry

@Composable
fun CountriesScreen(
    state: CountriesState,
    countries: List<ItemCountry>,
    onEventUI: (CountriesEventUI) -> Unit,
) {
    BackHandler(enabled = true) {
        onEventUI(OnBackStack)
    }
    Scaffold(
        topBar = {
            SearchCountry(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 10.dp),
                searchQueryCountry = state.searchCountry,
                placeholder = stringResource(id = R.string.search_country),
                onSearchQueryCountry = {
                    onEventUI(OnSearchQueryCountry(it))
                },
                onClearSearchQueryCountry = {
                    onEventUI(OnClearSearchQueryCountry)
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (state.loading)
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary, modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.Center)
                )
            else if (countries.isNotEmpty()) {
                CountryList(countries) {
                    onEventUI(OnSelectCountry(it))
                }
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Warning(state.message)
                }
            }
        }
    }
}

@Composable
private fun CountryList(countries: List<ItemCountry>, onSelectCountryCode: (ItemCountry) -> Unit) {
    LazyColumn {
        items(countries) { item ->
            CardItemCountry(
                name = item.official,
                capital = item.capital,
                image = item.image,
                onSelectCountry = {
                    onSelectCountryCode(item)
                })
        }
    }
}