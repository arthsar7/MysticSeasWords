package com.Mystic.Seas.Words

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Mystic.Seas.Words.ui.theme.appFont

@Composable
fun ExitConfirmationScreen(
    onReturn: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background
        Image(
            painter = painterResource(id = R.drawable.mainbg),
            contentScale = ContentScale.Crop,
            contentDescription = "Main Background",
            modifier = Modifier.fillMaxSize()
        )

        // Exit Text
        Text(
            text = "EXIT",
            style = TextStyle(
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = appFont
            ),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 50.dp)
                .drawBehind {
                    // Draw the outline
                    val outlineColor = Color.Red
                    val outlineWidth = 2.dp.toPx()
                }
                .padding(8.dp)
                .graphicsLayer { translationY = 2.dp.toPx() } // Adjust for better visual appearance
        )

        // Button YES
        Image(
            painter = painterResource(id = R.drawable.yes),
            contentDescription = "Yes Button",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 32.dp, bottom = 16.dp)
                .clickable {

                }
        )

        // Button NO
        Image(
            painter = painterResource(id = R.drawable.no),
            contentDescription = "No Button",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 32.dp, bottom = 16.dp)
                .clickable {
                    onReturn()
                }
        )
    }
}

