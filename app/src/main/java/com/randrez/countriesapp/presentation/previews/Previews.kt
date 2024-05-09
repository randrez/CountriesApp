package com.randrez.countriesapp.presentation.previews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.randrez.countriesapp.presentation.countries.SearchCountry


@Preview(showSystemUi = true)
@Composable
fun PreviewSearchCountry() {
    SearchCountry(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .background(Color.White),
        searchQueryCountry = "",
        onSearchQueryCountry = {},
        onClearSearchQueryCountry = {}
    )
}