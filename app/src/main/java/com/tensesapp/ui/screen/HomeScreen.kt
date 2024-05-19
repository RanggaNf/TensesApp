package com.tensesapp.ui.screen

import android.media.SoundPool
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tensesapp.R
import com.tensesapp.data.model.Materi
import com.tensesapp.data.model.MateriModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.tensesapp.ui.theme.colorPrimary
import com.tensesapp.ui.theme.gray
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Leaderboard
import androidx.compose.material.icons.rounded.Star
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.tensesapp.navigation.Destination


@Composable
fun HomeScreen(navController: NavController) {
    var bookmarkedFlowers by remember { mutableStateOf(listOf<Materi>()) } // State untuk menyimpan daftar bookmark

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            SlidingBanner()
        }
        item {
            CategoryView(navController = navController)
        }
        item {
            Row(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Materi Populer",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.helvetica_neue_bold))
                    ),
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "View All",
                    style = MaterialTheme.typography.subtitle2.copy(color = colorPrimary)
                )
            }
        }
        item {
            PopularFlowersList(bookmarkedFlowers) { flower ->
                // Handle bookmark toggling
                bookmarkedFlowers = if (bookmarkedFlowers.contains(flower)) {
                    bookmarkedFlowers - flower
                } else {
                    bookmarkedFlowers + flower
                }
            }
        }
    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
private fun SlidingBanner() {
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState) {
        // Collect from the pager state a snapshotFlow reading the currentPage
        snapshotFlow { pagerState.currentPage }.collect { page ->
//            AnalyticsService.sendPageSelectedEvent(page)
        }
    }

    HorizontalPager(
        count = 3,
        state = pagerState,
        itemSpacing = 20.dp,
    ) { page ->
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            bitmap = ImageBitmap.imageResource(id = R.drawable.banner_lns),
            contentScale = ContentScale.FillWidth,
            contentDescription = "sliding_banner_image"
        )
    }

    HorizontalPagerIndicator(
        pagerState = pagerState,
        modifier = Modifier
            .padding(16.dp),
    )
}

@Composable
private fun CategoryView(navController: NavController) {

    val context = LocalContext.current

    // Fungsi untuk memainkan suara klik tombol
    fun playButtonClickSound() {
        val soundPool = SoundPool.Builder().setMaxStreams(1).build()
        val buttonClickSoundId = soundPool.load(context, R.raw.clcik_materi, 1)
        soundPool.setOnLoadCompleteListener { _, _, _ ->
            soundPool.play(buttonClickSoundId, 1F, 1F, 0, 0, 1F)
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        RoundedCornerIconButton(
            modifier = Modifier.weight(1f),
            icon = Icons.Rounded.Favorite,
            contentDescription = "Favorite",
            onClick = {
                navController.navigate(Destination.favoriteScreen)
                playButtonClickSound()
            }
        ) {
            TextWithIconDescription(text = "Favorite", icon = Icons.Rounded.Favorite)
        }
        Spacer(modifier = Modifier.size(16.dp))
        RoundedCornerIconButton(
            modifier = Modifier.weight(1f),
            icon = Icons.Rounded.Leaderboard,
            contentDescription = "Ranking",
            onClick = {
                navController.navigate(Destination.onDevelop)
                playButtonClickSound()
            }
        ) {
            TextWithIconDescription(text = "Ranking", icon = Icons.Rounded.Leaderboard)
        }
        Spacer(modifier = Modifier.size(16.dp))
        RoundedCornerIconButton(
            modifier = Modifier.weight(1f),
            icon = Icons.Rounded.Star,
            contentDescription = "Popular",
            onClick = {
                navController.navigate(Destination.onDevelop)
                playButtonClickSound()
            }
        ) {
            TextWithIconDescription(text = "Popular", icon = Icons.Rounded.Star)
        }
        Spacer(modifier = Modifier.size(16.dp))
        RoundedCornerIconButton(
            modifier = Modifier.weight(1f),
            icon = Icons.Rounded.BookmarkBorder,
            contentDescription = "Saved",
            onClick = {
                navController.navigate(Destination.onDevelop)
                playButtonClickSound()
            }
        ) {
            TextWithIconDescription(text = "Saved", icon = Icons.Rounded.BookmarkBorder)
        }
    }
}
@Composable
fun RoundedCornerIconButton(modifier: Modifier, icon: ImageVector, contentDescription: String, onClick: () -> Unit, content: @Composable () -> Unit) {
    val context = LocalContext.current

    // Fungsi untuk memainkan suara klik tombol
    fun playButtonClickSound() {
        val soundPool = SoundPool.Builder().setMaxStreams(1).build()
        val buttonClickSoundId = soundPool.load(context, R.raw.button_click_sound, 1)
        soundPool.setOnLoadCompleteListener { _, _, _ ->
            soundPool.play(buttonClickSoundId, 1F, 1F, 0, 0, 1F)
        }
    }
    Box(
        modifier = modifier
            .padding(vertical = 8.dp) // Added vertical padding for better spacing
            .fillMaxHeight()
            .aspectRatio(1f)
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            // Make the entire box clickable
            .clickable { onClick(
            )
            }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.layoutId("icon"),
                imageVector = icon,
                contentDescription = contentDescription
            )
            content()
        }
    }
}


@Composable
fun TextWithIconDescription(text: String, icon: ImageVector) {
    Text(
        text = text,
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(top = 4.dp)
    )
}

@Composable
private fun PopularFlowersList(bookmarkedFlowers: List<Materi>, onBookmarkToggle: (Materi) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(MateriModel.list.size) { index ->
            val flower = MateriModel.list[index]
            val isBookmarked = bookmarkedFlowers.contains(flower)
            FlowerCard(flower, isBookmarked, onBookmarkToggle)
        }
    }
}

@Composable
private fun FlowerCard(flower: Materi, isBookmarked: Boolean, onBookmarkToggle: (Materi) -> Unit) {


    Card(
        shape = RoundedCornerShape(14.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(10.dp)
            .width(180.dp)
            .clickable { onBookmarkToggle(flower)
            } // Menangani klik pada kartu
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                bitmap = ImageBitmap.imageResource(id = flower.image),
                contentDescription = "flower_card"
            )
            Row(modifier = Modifier.padding(top = 20.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = flower.name,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Left,
                            fontFamily = FontFamily(Font(R.font.nato_bold))
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .background(
                            color = colorPrimary,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable { onBookmarkToggle(flower) } // Menangani klik pada ikon bookmark
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        tint = Color.White,
                        modifier = Modifier.padding(10.dp),
                        contentDescription = "flower_card_icon"
                    )
                }
            }
        }
    }
}