package com.Mystic.Seas.Words

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay

@Composable
fun Loading(
    onNext: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(1599)
        onNext()
    }
    Image(
        painter = painterResource(id = R.drawable.loading),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
}