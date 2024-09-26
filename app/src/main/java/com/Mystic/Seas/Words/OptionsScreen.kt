package com.Mystic.Seas.Words

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OptionsScreen(
    onReturn: () -> Unit
) {
    var isSoundActive by remember { mutableStateOf(
        Prefs.soundVolume != 0f
    ) }
    var isMusicActive by remember { mutableStateOf(
        Prefs.musicVolume != 0f
    ) }

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
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedText(
                text = "OPTIONS",
                outlineColor = Color.Red,
                color = Color.White,
                modifier = Modifier
                    .padding(8.dp),
                fontSize = 32.sp
            )
            Box(
                modifier = Modifier
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
                            .size(80.dp)
                            .clickable {
                                isSoundActive = !isSoundActive
                                Prefs.soundVolume = if (isSoundActive) 0.6f else 0f
                                SoundManager.setSoundVolume()
                            }
                            .padding(8.dp)
                    )

                    // Music Button
                    Image(
                        painter = painterResource(id = if (isMusicActive) R.drawable.active_music else R.drawable.unactive_music),
                        contentDescription = "Music",
                        modifier = Modifier
                            .size(80.dp)
                            .clickable {
                                isMusicActive = !isMusicActive
                                Prefs.musicVolume = if (isMusicActive) 0.6f else 0f
                                SoundManager.setMusicVolume()
                            }
                            .padding(8.dp)
                    )
                }
            }
        }

        // Dialog Background
    }
}

