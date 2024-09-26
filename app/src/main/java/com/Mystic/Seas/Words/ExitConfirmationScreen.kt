package com.Mystic.Seas.Words

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExitConfirmationScreen(
    onReturn: () -> Unit
) {
    val activity = LocalContext.current as? Activity
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.mainbg),
            contentScale = ContentScale.Crop,
            contentDescription = "Main Background",
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            OutlinedText(
                text = "EXIT",
                fontSize = 32.sp,
                color = Color.White,
                outlineColor = Color.Red,
                modifier = Modifier
                    .padding(8.dp)
                    .graphicsLayer { translationY = 2.dp.toPx() } // Adjust for better visual appearance
            )
            Image(
                painter = painterResource(id = R.drawable.yes),
                contentDescription = "Yes Button",
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .clickable {
                        activity?.finishAndRemoveTask()
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.no),
                contentDescription = "No Button",
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .clickable {
                        onReturn()
                    }
            )
        }
    }
}

