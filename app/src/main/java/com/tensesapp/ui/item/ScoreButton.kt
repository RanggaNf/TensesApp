package com.tensesapp.ui.item

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tensesapp.quiz.fontCompose
import com.tensesapp.ui.theme.SecondaryVariant
import com.tensesapp.ui.theme.TopColor

@Composable
fun ScoreButton(text: String, onClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            enabled = true,
            onClick = {
                onClick.invoke()
            },
            modifier = Modifier
                .padding(start = 0.dp, end = 0.dp, bottom = 0.dp, top = 0.dp)
                .width(80.dp)
                .height(70.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = TopColor
            ),
            shape = RoundedCornerShape(bottomEnd = 60.dp, bottomStart = 60.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 6.dp
            )
        ) {
            Text(text = text, color = SecondaryVariant, fontFamily = fontCompose, fontSize = 36.sp)
        }
    }
}
