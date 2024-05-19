package com.tensesapp.ui.screen

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tensesapp.data.di.Injection
import com.tensesapp.data.model.Materi
import com.tensesapp.ui.common.UiState
import com.tensesapp.ui.viewmodel.DetailViewModel
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.tensesapp.R
import com.tensesapp.ui.viewmodel.ViewModelFactory
import java.util.Random

@Composable
fun DetailScreen(
    materiId: Int,
    navigateBack: () -> Unit,
    context: Context = LocalContext.current,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.materiRepository(context), context)
    )
) {
    val uiState = viewModel.uiState.collectAsState(initial = UiState.Loading).value

    DisposableEffect(Unit) {
        val random = Random()
        val musicResources = listOf(R.raw.music_materi1, R.raw.music_materi2, R.raw.music_materi3)
        val musicIndex = random.nextInt(musicResources.size)
        val mediaPlayer = MediaPlayer.create(context, musicResources[musicIndex])
        mediaPlayer.isLooping = true
        mediaPlayer.start()

        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }

    when (uiState) {
        is UiState.Loading -> {
            viewModel.getMateriById(materiId)
        }

        is UiState.Success -> {
            val materi = uiState.data
            DetailInfo(
                materi = materi,
                navigateBack = navigateBack,
                onFavoriteButtonClick = { id, state ->
                    viewModel.updateMateri(id, state)
                }
            )
        }

        is UiState.Error -> {
            // Handle error state
        }
    }
}
@Composable
fun DetailInfo(
    materi: Materi,
    navigateBack: () -> Unit,
    onFavoriteButtonClick: (id: Int, state: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Image(
                painter = painterResource(materi.image),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = materi.name,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Meaning:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = materi.meaning,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Definition:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = materi.definition,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Formula :",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )

            // Always display formula_verb
            // Always display formula_verb
            // Always display formula_verb
            Image(
                painter = painterResource(materi.formula_verb),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f) // Aspect ratio set to 1:1 for square
            )

// Check if the materiId is such that formula_nonverb should not be displayed
            if (materi.id != 3) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(materi.formula_nonverb),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f) // Add padding to the top to eliminate the gap
                    )
                }
            }

            // Text "Have A Good Day"
            Text(
                text = "Have A Good Day",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )
        }
        IconButton(
            onClick = navigateBack,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopStart)
                .clip(CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back),
                modifier = Modifier.size(36.dp)
            )
        }
    }
}
