package com.randrez.countriesapp.presentation.country

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.randrez.countriesapp.R
import com.randrez.countriesapp.presentation.LoadImageInDetail
import com.randrez.countriesapp.presentation.LoadImageItemComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryScreen(state: CountryState) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = state.title)
        })
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    LoadImageInDetail(
                        image = state.image.ifEmpty { "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg" }
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                state.country?.let {
                    TextWithTitle(
                        title = R.string.name,
                        value = it.name
                    )
                    TextWithTitle(
                        title = R.string.official_name,
                        value = it.officialName
                    )
                    TextWithTitle(
                        title = R.string.language,
                        value = it.language
                    )
                    TextWithTitle(
                        title = R.string.language,
                        value = it.language
                    )
                    TextWithTitle(
                        title = R.string.population,
                        value = it.population
                    )
                    TextWithTitle(
                        title = R.string.capital,
                        value = it.capital
                    )
                    if (state.borders.isNotEmpty()) {
                        ElevatedCard(
                            modifier = Modifier.padding(5.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(
                                contentColor = Color.White
                            )
                        ) {
                            Column {
                                state.borders.forEach { itemCountry ->

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TextWithTitle(@StringRes title: Int, value: String, fontSize: Int = 18) {
    Row(modifier = Modifier.padding(2.dp)) {
        Text(
            text = stringResource(id = title),
            color = MaterialTheme.colorScheme.primary,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = value,
            fontSize = fontSize.sp,
            modifier = Modifier.padding(10.dp)
        )
    }
}

