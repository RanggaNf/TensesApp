package com.tensesapp.ui.item

import android.media.SoundPool
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.tensesapp.data.model.Materi
import com.tensesapp.R
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ItemMateri(
    materi: Materi,
    onItemClick: (Materi) -> Unit,
    onFavoriteIconClick: (Materi) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val soundPool = remember { SoundPool.Builder().setMaxStreams(1).build() }
    val buttonClickSoundId = remember {
        soundPool.load(context, R.raw.clcik_materi, 1)
    }

    fun playButtonClickSound() {
        soundPool.play(buttonClickSoundId, 1F, 1F, 0, 0, 1F)
    }

    var isFavorite by remember { mutableStateOf(materi.isFavorite) } // Track favorite state locally

    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onItemClick(materi)
                    playButtonClickSound()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = materi.image),
                contentDescription = "materi_image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = materi.name,
                    fontSize = 18.sp, // Ukuran font lebih besar
                    fontWeight = FontWeight.Bold, // Font lebih tebal
                    maxLines = 3
                )
                // Icon favorite di samping nama materi
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite Icon",
                        tint = if (isFavorite) Color.Red else Color.Gray,
                        modifier = Modifier
                            .padding(start = 1.dp)
                            .clickable {
                                isFavorite = !isFavorite // Update local state
                                onFavoriteIconClick(materi.copy(isFavorite = isFavorite))
                                playButtonClickSound()
                            }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    // Teks "Favorite" di samping ikon favorit
                    Text(
                        text = "Favorite",
                        color = if (isFavorite) Color.Red else Color.Gray,
                        fontSize = 14.sp,
                        modifier = Modifier.clickable {
                            isFavorite = !isFavorite // Update local state
                            onFavoriteIconClick(materi.copy(isFavorite = isFavorite))
                            playButtonClickSound()
                        }
                    )
                }
            }
        }
    }
}
