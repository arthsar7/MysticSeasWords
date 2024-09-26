package com.Mystic.Seas.Words


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Mystic.Seas.Words.ui.theme.appFont

@Composable
fun GameWinScreen(
    result : Boolean = true,
    onRetry: () -> Unit,
    onNext: () -> Unit,
    onExit: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(id = R.drawable.winorlosebg), contentScale = ContentScale.Crop),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(72.dp))

            // Увеличиваем размер текста "WINNER"
            OutlinedText(
                text = if (result) "WINNER" else "DEFEAT",
                fontSize = 64.sp, // Увеличен размер текста
                color = Color.Yellow,
                outlineColor = Color.Red
            )

            Spacer(modifier = Modifier.height(36.dp))

            // Вставляем Image вместо Box с winlosebg
            Box(
                modifier = Modifier
                    .size(300.dp) // Размер фона (изображения)
                    .padding(16.dp), // Оставляем внутренние отступы для текста
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.winlosebg),
                    contentDescription = "Result background",
                    modifier = Modifier.fillMaxSize(), // Заполняем весь бокс
                    contentScale = ContentScale.FillBounds // Масштабируем изображение
                )

                // Элементы внутри изображения
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(12.dp)
                ) {
                    // "GREAT!" текст
                    OutlinedText(
                        text = if (result) "GREAT!" else "TIME IS OVER",
                        fontSize = 32.sp, // Увеличен размер текста
                        color = Color.Yellow,
                        outlineColor = Color.Red
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // "LEVEL" текст
                    OutlinedText(
                        text = "LEVEL",
                        fontSize = 32.sp, // Увеличен размер текста
                        color = Color.Yellow,
                        outlineColor = Color.Red
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Уровень
                    OutlinedText(
                        text = if (result) (Prefs.level - 1).toString() else Prefs.level.toString(), // Уровень
                        fontSize = 64.sp, // Увеличен размер текста
                        color = Color.Yellow,
                        outlineColor = Color.Red
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Кнопки меню и повторить
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Кнопка меню
                        Image(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = "Menu",
                            modifier = Modifier
                                .size(48.dp)
                                .clickable {
                                    onExit()
                                }
                        )
                        Spacer(modifier = Modifier.width(16.dp))

                        // Кнопка повторить
                        Image(
                            painter = painterResource(id = R.drawable.ic_retry),
                            contentDescription = "Retry",
                            modifier = Modifier
                                .size(48.dp)
                                .clickable {
                                    onRetry()
                                }
                        )
                    }
                }
            }

            // Добавляем Spacer для поднятия кнопки Next
            Spacer(modifier = Modifier.height(22.dp)) // Поднимаем кнопку на 32.dp от нижней части

            // Кнопка Next
            Image(
                painter = painterResource(id = if (result && Prefs.level < 20) R.drawable.nextbutton else R.drawable.try_again),
                contentDescription = "Next",
                modifier = Modifier
                    .clickable {
                        if (result && Prefs.level < 20) {
                            onNext()
                        } else {
                            onRetry()
                        }
                    }
                    .fillMaxWidth(0.8f)
                    .height(64.dp)
            )
            Spacer(modifier = Modifier.height(22.dp))
        }
    }
}

@Composable
fun OutlinedText(
    text: String,
    fontSize: TextUnit,
    color: Color,
    outlineColor: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = fontSize,
            fontFamily = appFont, // Ваш шрифт
            color = color,
            fontWeight = FontWeight.Bold,
            shadow = Shadow(
                color = outlineColor,
                blurRadius = 3f
            )
        ),
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}
