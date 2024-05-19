package com.tensesapp.ui.item

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.tensesapp.ui.theme.ButtonColor

@Composable
fun TopProgressBar(progress: Float) {
    val currentProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow
        )
    )
    LinearProgressIndicator(
        modifier = Modifier
            .padding(start = 30.dp, end = 30.dp, bottom = 20.dp, top = 20.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp)),
        color = ButtonColor,
        backgroundColor = ButtonColor.copy(0.4f),
        progress = currentProgress
    )
}
