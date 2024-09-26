package com.Mystic.Seas.Words

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun MenuScreen(
    onNext: () -> Unit,
    onExit: () -> Unit,
    onOptions: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.mainbg),
                contentScale = ContentScale.FillBounds
            )
    ) {
        IconButton(
            onClick = onOptions,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .size(60.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_settings),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(50.dp)
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .shadow(elevation = 1.dp, shape = CircleShape)
                    .size(280.dp, 85.dp)
                    .paint(
                        painter = painterResource(id = R.drawable.play_button),
                        contentScale = ContentScale.FillBounds
                    )
                    .clickable { onNext() }
            )

            Spacer(modifier = Modifier.padding(8.dp))
            Box(
                modifier = Modifier
                    .shadow(elevation = 1.dp, shape = CircleShape)
                    .size(280.dp, 85.dp)
                    .paint(
                        painter = painterResource(id = R.drawable.exit_button),
                        contentScale = ContentScale.FillBounds
                    )
                    .clickable { onExit() }
            )
        }
    }

}