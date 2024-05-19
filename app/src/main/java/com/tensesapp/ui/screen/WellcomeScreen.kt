package com.tensesapp.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tensesapp.R
import com.tensesapp.quiz.fontColor
import com.tensesapp.ui.item.WantedStars
import com.tensesapp.ui.theme.SecondColor
import com.tensesapp.ui.theme.TopColor

@Composable
fun WelcomeScreen(
    scoreText: String,
    isDone: Boolean,
    score: Int,
    isVisible: Boolean,
    topText: String,
    onClickStart: () -> Unit,
    buttonTextFirst: String,
    buttonTextSecond: String,
    buttonTextThird: String,
    onClickSecond: () -> Unit,
    onClickThird: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth()
            .background(TopColor)
            .padding(10.dp),
        verticalArrangement = Arrangement.Center
    ) {

        AnimatedVisibility(
            visible = isVisible
        ) {
            Image(
                painter = painterResource(id = R.drawable.quiz2),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 0.dp, start = 10.dp, end = 10.dp, bottom = 40.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp)),
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit
            )
        }

        Text(
            text = topText,
            color = fontColor,
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 0.dp),
            textAlign = TextAlign.Center,
            letterSpacing = 1.sp
        )

        val icon = when (score) {
            1 - 5 -> {
                painterResource(id = R.drawable.three)
            }
            5 -> {
                painterResource(id = R.drawable.three)
            }
            6 - 8 -> {
                painterResource(id = R.drawable.two)
            }
            8 - 9 -> {
                painterResource(id = R.drawable.two)
            }
            10 -> {
                painterResource(id = R.drawable.one)
            }
            else -> {
                painterResource(id = R.drawable.three)
            }
        }

        if (isDone) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = scoreText,
                    color = fontColor,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 0.dp),
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.sp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    WantedStars(
                        paddingValues = PaddingValues(top = 20.dp, bottom = 0.dp),
                        count = score,
                    )
                }
                Image(
                    painter = icon,
                    contentDescription = "reward icon",
                    modifier = Modifier
                        .padding(start = 0.dp, end = 0.dp, top = 20.dp, bottom = 0.dp)
                        .width(100.dp)
                        .height(100.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }

        if (isVisible) {
            Text(
                text = "WITH US!",
                color = fontColor,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                letterSpacing = 1.sp
            )
        }


        Row(
            modifier = Modifier
                .padding(bottom = 10.dp, top = 40.dp, start = 10.dp, end = 10.dp)
        ) {
            Button(
                onClick = { onClickStart.invoke() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = SecondColor,
                ),
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
                    .weight(1f),
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(
                    text = buttonTextFirst,
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    textAlign = TextAlign.Center
                )
            }
            Button(
                onClick = { onClickSecond.invoke() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = SecondColor,
                ),
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
                    .weight(1f),
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(
                    text = buttonTextSecond,
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        // Tampilkan tombol ketiga hanya saat kuis telah selesai
        if (isDone) {
            Button(
                onClick = { onClickThird.invoke() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = SecondColor,
                ),
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(
                    text = buttonTextThird,
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
