package com.randrez.countriesapp.presentation.country

import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.randrez.countriesapp.R
import com.randrez.countriesapp.presentation.CardItemCountry
import com.randrez.countriesapp.presentation.LoadImageInDetail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryScreen(state: CountryState, obBackStack: () -> Unit) {

    BackHandler(enabled = true) {
        obBackStack()
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = state.title)
        }, navigationIcon = {
            IconButton(onClick = obBackStack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = "Arrow Back",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White))
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (state.loading)
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.Center)
                )
            else
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 15.dp),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                LoadImageInDetail(image = state.image.ifEmpty { "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available.jpg" })
                            }
                            Spacer(modifier = Modifier.padding(5.dp))
                            state.country?.let {
                                TextWithTitle(
                                    title = R.string.name, value = it.name
                                )
                                TextWithTitle(
                                    title = R.string.code, value = it.code
                                )
                                TextWithTitle(
                                    title = R.string.official_name, value = it.officialName
                                )
                                TextWithTitle(
                                    title = R.string.capital, value = it.capital
                                )
                                TextWithTitle(
                                    title = R.string.region, value = it.region
                                )
                                TextWithTitle(
                                    title = R.string.population, value = it.population
                                )
                                if (state.borders.isNotEmpty()) {
                                    Divider(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 20.dp),
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.borders),
                                            color = MaterialTheme.colorScheme.primary,
                                            fontSize = 19.sp,
                                            fontWeight = FontWeight.ExtraBold,
                                            modifier = Modifier.padding(10.dp)
                                        )
                                    }
                                    state.borders.forEach { itemCountry ->
                                        CardItemCountry(
                                            fontSizeName = 18,
                                            colorName = Color.White,
                                            colorText = Color.White,
                                            containerColor = MaterialTheme.colorScheme.primary,
                                            code = itemCountry.code,
                                            name = "${stringResource(id = R.string.name)} ${itemCountry.name}",
                                            capital = itemCountry.capital
                                        )
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
            modifier = Modifier.padding(vertical = 5.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = value,
            fontSize = fontSize.sp,
            modifier = Modifier.padding(vertical = 5.dp),
            color = Color.Gray
        )
    }
}
