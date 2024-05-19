package com.tensesapp.ui.item

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tensesapp.quiz.fontColor
import com.tensesapp.quiz.fontCompose
import com.tensesapp.ui.theme.ButtonColor
import com.tensesapp.ui.theme.RaisinBlack
import com.tensesapp.ui.theme.SecondColor

@Composable
fun OptionItem(text: String, index: Int, selected: Boolean, onClick: (Int) -> Unit) {
    val fontSize by remember {
        mutableStateOf(42.sp)
    }
    val colorListGradient = listOf(
        SecondColor,
        ButtonColor,
    )
    val currentFontSizePx = with(LocalDensity.current) { fontSize.toPx() }
    val currentFontSizeDoublePx = currentFontSizePx * 2
    val infiniteTransition = rememberInfiniteTransition()
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = currentFontSizeDoublePx,
        animationSpec = infiniteRepeatable(animation = tween(1800, easing = LinearEasing))
    )
    val brush = Brush.linearGradient(
        colorListGradient,
        start = Offset(offset, offset),
        end = Offset(offset + currentFontSizePx, offset + currentFontSizePx),
        tileMode = TileMode.Mirror
    )
    var padding by remember {
        mutableStateOf(8.dp)
    }


    val paddingValue by animateDpAsState(targetValue = padding)
    padding = when (selected) {
        true -> {
            12.dp
        }
        else -> {
            8.dp
        }
    }
    Text(
        text = text,
        modifier = Modifier
            .padding(top = 4.dp, start = 4.dp, end = 4.dp, bottom = 4.dp)
            .shadow(elevation = 4.dp, RoundedCornerShape(10.dp))
            .clickable {
                onClick.invoke(index)
            }
            .background(
                if (selected) SecondColor else Color(0xFFBDC3DA),
                RoundedCornerShape(10.dp)
            )
            .fillMaxWidth()
            .border(
                width = if (selected) 1.dp else Dp.Unspecified,
                brush = brush,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(paddingValue),
        textAlign = TextAlign.Center,
        color = if (selected) fontColor else RaisinBlack,
        fontFamily = fontCompose,
        fontSize = 16.sp,
        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
    )
}
