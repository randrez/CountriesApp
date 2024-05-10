package com.randrez.countriesapp.presentation.countries

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun SearchCountry(
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    searchQueryCountry: String,
    onSearchQueryCountry: (String) -> Unit,
    onClearSearchQueryCountry: () -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {
        TextField(
            value = searchQueryCountry,
            onValueChange = onSearchQueryCountry,
            modifier = Modifier.fillMaxWidth(),
            shape = CircleShape,
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
            placeholder = {
                placeholder?.let {
                    Text(
                        text = placeholder,
                        color = Color.Gray,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            },
            singleLine = true,
            trailingIcon = {
                if (searchQueryCountry.length > 0)
                    IconButton(onClick = onClearSearchQueryCountry) {
                        Icon(
                            imageVector = Icons.Outlined.Clear,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }
                else
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null,
                        tint = Color.Gray
                    )
            },
            colors = TextFieldDefaults.colors(
                disabledTextColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            )
        )
    }
}