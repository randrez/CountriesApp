package com.randrez.countriesapp.presentation.commons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.randrez.countriesapp.R

@Composable
fun CardItemCountry(
    name: String,
    capital: String,
    code: String? = null,
    image: String? = null,
    fontSizeName: Int = 19,
    containerColor:Color = Color.White,
    colorName: Color = MaterialTheme.colorScheme.primary,
    colorText:Color = Color.Gray,
    onSelectCountry: (() -> Unit)? = null
) {
    ElevatedCard(
        modifier = Modifier
            .padding(vertical = 5.dp, horizontal = 15.dp)
            .clickable(onSelectCountry != null) {
                onSelectCountry?.let { onSelect ->
                    onSelect()
                }
            },
        colors = CardDefaults.elevatedCardColors(
            containerColor = containerColor
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 5.dp
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            image?.let {
                LoadImageItemComponent(image)
            }
            Column(
                modifier = Modifier.padding(start = 5.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row {
                    Text(
                        text = name,
                        color = colorName,
                        fontSize = fontSizeName.sp
                    )
                }
                code?.let {
                    Row {
                        Text(
                            text = "${stringResource(id = R.string.code)} $code",
                            color = colorText
                        )
                    }
                } ?: Spacer(modifier = Modifier.padding(10.dp))
                Row {
                    Text(
                        text = "${stringResource(id = R.string.capital)} $capital",
                        color = colorText
                    )
                }
            }
        }
    }
}