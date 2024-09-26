package com.Mystic.Seas.Words

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import com.Mystic.Seas.Words.ui.theme.appFont
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun GameScreen(
    onComplete: () -> Unit
) {
    val word = "PIRATES" // Слово для игры
    var currentWord by remember { mutableStateOf("") }
    var timer by remember { mutableStateOf(30) }
    val letters = remember { word.toList().shuffled() } // Перемешиваем буквы только один раз
    var selectedLetters by remember { mutableStateOf(listOf<Char>()) }

    // Таймер на 30 секунд
    LaunchedEffect(timer) {
        while (timer > 0) {
            delay(1000L)
            timer -= 1
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.mainbg), contentScale = ContentScale.Crop),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Верхняя панель с кнопками
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "Menu",
                    modifier = Modifier
                        .size(48.dp)
                        .clickable {

                        }
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = "Settings",
                    modifier = Modifier
                        .size(48.dp)
                        .clickable {

                        }
                )
            }

            // Уровень
            Box(
                modifier = Modifier
                    .size(150.dp, 50.dp)
                    .paint(
                        painterResource(id = R.drawable.rounded_gradient_bg),
                        contentScale = ContentScale.Fit
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "LEVEL 1",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontFamily = appFont,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(26.dp))

            // Поле для отображения правильных букв
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                word.forEachIndexed { index, letter ->
                    Box(
                        modifier = Modifier
                            .size(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.poledlyabukv),
                            contentDescription = "Letter box",
                            modifier = Modifier.fillMaxSize(), // Заполняем весь контейнер
                            contentScale = ContentScale.FillBounds // Растягиваем изображение, чтобы оно соответствовало размеру Box
                        )

                        // Буква, если она уже выбрана
                        Text(
                            text = if (index < selectedLetters.size) selectedLetters[index].toString() else "",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 32.sp,
                                fontFamily = appFont,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }

            // Разбросанные буквы
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    letters.chunked(3).forEach { row ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            row.forEach { letter ->
                                Text(
                                    text = letter.toString(),
                                    style = TextStyle(
                                        fontSize = 36.sp,
                                        color = Color.Red,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = appFont,
                                        shadow = Shadow(Color.Black, blurRadius = 4f)
                                    ),
                                    modifier = Modifier
                                        .clickable {
                                            if (letter == word[selectedLetters.size]) {
                                                selectedLetters = selectedLetters + letter
                                            }
                                        }
                                )
                            }
                        }
                    }
                }
            }

            // Таймер и кнопка завершения
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(150.dp, 50.dp)
                        .paint(
                            painterResource(id = R.drawable.back_gradient_timer),
                            contentScale = ContentScale.Fit
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$timer",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontFamily = appFont,
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Кнопка завершения
                Box(
                    modifier = Modifier
                        .size(200.dp, 60.dp)
                        .clickable{

                        }
                        .paint(painterResource(id = R.drawable.completebutton), contentScale = ContentScale.Fit),
                    contentAlignment = Alignment.Center
                ){

                }
            }
        }
    }
}