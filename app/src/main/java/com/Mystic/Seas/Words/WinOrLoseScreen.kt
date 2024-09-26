package com.Mystic.Seas.Words


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.Mystic.Seas.Words.ui.theme.appFont

@Preview
@Composable
fun GameWinScreen() {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

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
                text = "WINNER",
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
                        text = "GREAT!",
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
                        text = "1", // Уровень
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
                                .clickable { /* Действие при нажатии меню */ }
                        )
                        Spacer(modifier = Modifier.width(16.dp))

                        // Кнопка повторить
                        Image(
                            painter = painterResource(id = R.drawable.ic_retry),
                            contentDescription = "Retry",
                            modifier = Modifier
                                .size(48.dp)
                                .clickable { /* Действие при нажатии повторить */ }
                        )
                    }
                }
            }

            // Добавляем Spacer для поднятия кнопки Next
            Spacer(modifier = Modifier.height(22.dp)) // Поднимаем кнопку на 32.dp от нижней части

            // Кнопка Next
            Image(
                painter = painterResource(id = R.drawable.nextbutton),
                contentDescription = "Next",
                modifier = Modifier
                    .clickable { /* Действие при нажатии Next */ }
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
    outlineColor: Color
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
                blurRadius = 8f
            )
        ),
        textAlign = TextAlign.Center
    )
}
