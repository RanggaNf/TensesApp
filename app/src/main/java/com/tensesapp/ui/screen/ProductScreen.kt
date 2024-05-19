package com.tensesapp.ui.screen

import android.media.SoundPool
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tensesapp.R
import com.tensesapp.navigation.Destination
import com.tensesapp.ui.item.QuizItemCard
import com.tensesapp.ui.theme.colorPrimary

@Composable
fun ProductScreen(navController: NavController) {
    val context = LocalContext.current

    // Fungsi untuk memainkan suara klik tombol
    fun playButtonClickSound() {
        val soundPool = SoundPool.Builder().setMaxStreams(1).build()
        val buttonClickSoundId = soundPool.load(context, R.raw.button_click_sound, 1)
        soundPool.setOnLoadCompleteListener { _, _, _ ->
            soundPool.play(buttonClickSoundId, 1F, 1F, 0, 0, 1F)
        }
    }
    Surface(color = Color.White) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    navController.navigate(Destination.AboutScreen)
                    playButtonClickSound()
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(bottom = 12.dp, start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimary)
            ) {
                Text(
                    text = "TENTANG APLIKASI",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navController.navigate(Destination.OriginScreen)
                    playButtonClickSound()
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(bottom = 12.dp, start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorPrimary)
            ) {
                Text(
                    text = "ORISINALITAS APLIKASI",
                    color = Color.White
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewProductScreen() {
    // Dummy NavController for preview
    val navController = rememberNavController()
    ProductScreen(navController = navController)
}
