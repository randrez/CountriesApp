package com.randrez.countriesapp.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter.State
import coil.compose.rememberImagePainter
import coil.request.CachePolicy

@OptIn(ExperimentalCoilApi::class)
@Composable
fun LoadImageItemComponent(
    image: String,
    background: Color = Color.White
) {
    val imageLoader = ImageLoader.Builder(LocalContext.current).build()
    val painter = rememberImagePainter(data = image, imageLoader = imageLoader) {
        crossfade(true)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
    }

    when (painter.state) {
        is State.Loading -> {
            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(5.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = background
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 5.dp
                )
            ) {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier
                        .size(80.dp)
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.secondary, modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.Center)
                    )
                }
            }

        }

        is State.Error -> {
            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(5.dp),
                shape = CircleShape,
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 5.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = background
                )
            ) {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier
                        .size(80.dp)
                        .background(background)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Warning,
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .align(Alignment.Center),
                        tint = Color.Red
                    )
                }
            }
        }

        else -> {
            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(5.dp),
                shape = CircleShape,
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 5.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = background
                )
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun LoadImageInDetail(image: String) {
    val imageLoader = ImageLoader.Builder(LocalContext.current).build()
    val painter = rememberImagePainter(data = image, imageLoader = imageLoader) {
        crossfade(true)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
    }

    when (painter.state) {
        is State.Loading -> {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondary, modifier = Modifier
                        .size(180.dp)
                        .align(Alignment.Center)
                )
            }
        }

        is State.Error -> {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Warning,
                    contentDescription = null,
                    modifier = Modifier
                        .size(180.dp)
                        .align(Alignment.Center),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }

        else -> {
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .border(BorderStroke(1.dp, Color.DarkGray))
                    .width(300.dp)
                    .height(100.dp)
            )
        }
    }
}