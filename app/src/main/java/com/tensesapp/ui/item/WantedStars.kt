package com.tensesapp.ui.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tensesapp.ui.theme.RaisinBlack
import com.tensesapp.ui.theme.SecondColor

@Composable
fun WantedStars(paddingValues: PaddingValues, count: Int) {
    val colors: List<Color> = List(10) {
        if (it < count) SecondColor else RaisinBlack
    }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        item {
            Spacer(
                modifier = Modifier
                    .padding(20.dp)
                    .height(1.dp)
            )
        }
        items(1) {
            colors.forEach { colorTint ->
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Yellow score star icon.",
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 0.dp),
                    tint = colorTint
                )
            }
        }
        item {
            Spacer(
                modifier = Modifier
                    .padding(20.dp)
                    .height(1.dp)
            )
        }
    }
}

