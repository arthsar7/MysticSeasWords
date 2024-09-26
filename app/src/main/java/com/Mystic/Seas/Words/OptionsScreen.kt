package com.Mystic.Seas.Words

import androidx.compose.runtime.Composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.Mystic.Seas.Words.ui.theme.appFont

@Composable
fun OptionsScreen(
    onReturn: () -> Unit
) {
    var isSoundActive by remember { mutableStateOf(true) }
    var isMusicActive by remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background
        Image(
            painter = painterResource(id = R.drawable.mainbg),
            contentScale = ContentScale.Crop,
            contentDescription = "Main Background",
            modifier = Modifier.fillMaxSize()
        )

        // Menu Icon
        Image(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = "Menu Icon",
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    onReturn()
                }
        )

        // Title with white text and red outline
        Text(
            text = "OPTIONS",
            style = TextStyle(
                fontSize = 32.sp,
                color = Color.White,  // Changed to white
                fontWeight = FontWeight.Bold,
                fontFamily = appFont
            ),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 50.dp)
                .border(2.dp, Color.Red, RoundedCornerShape(8.dp))
                .padding(8.dp)
        )

        // Dialog Background
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .paint(painterResource(id = R.drawable.option_dialog_bg), contentScale = ContentScale.Fit)
                .padding(16.dp)
                .size(320.dp, 200.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center, // Centering the buttons vertically
                modifier = Modifier.fillMaxSize() // Ensure Column takes full height
            ) {
                // Sound Button
                Image(
                    painter = painterResource(id = if (isSoundActive) R.drawable.active_sound else R.drawable.inactive_sound),
                    contentDescription = "Sound",
                    modifier = Modifier
                        .clickable {
                            isSoundActive = !isSoundActive
                        }
                        .padding(8.dp)
                )

                // Music Button
                Image(
                    painter = painterResource(id = if (isMusicActive) R.drawable.active_music else R.drawable.unactive_music),
                    contentDescription = "Music",
                    modifier = Modifier
                        .clickable {
                            isMusicActive = !isMusicActive
                        }
                        .padding(8.dp)
                )
            }
        }
    }
}

