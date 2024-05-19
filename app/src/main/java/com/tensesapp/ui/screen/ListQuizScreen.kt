import android.media.SoundPool
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tensesapp.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tensesapp.navigation.Destination
import com.tensesapp.ui.item.QuizItemCard

@Composable
fun ListQuizScreen(navController: NavController) {
    val context = LocalContext.current

    // Fungsi untuk memainkan suara klik tombol
    fun playButtonClickSound() {
        val soundPool = SoundPool.Builder().setMaxStreams(1).build()
        val buttonClickSoundId = soundPool.load(context, R.raw.button_click_sound, 1)
        soundPool.setOnLoadCompleteListener { _, _, _ ->
            soundPool.play(buttonClickSoundId, 1F, 1F, 0, 0, 1F)
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp) // Menambahkan spacing antar item
    ) {
        item {
            QuizItemCard(
                title = "Simple Present",
                image = R.drawable.simple_present,
                info = "Berisi 10 soal dengan 3 pilihan ganda",
                onItemClick = {
                    navController.navigate(Destination.SimplePresentQuiz)
                    playButtonClickSound()
                }
            )
        }
        item {
            QuizItemCard(
                title = "Simple Past",
                image = R.drawable.simple_past,
                info = "Berisi 10 soal dengan 3 pilihan ganda",
                onItemClick = {
                    navController.navigate(Destination.SimplePastQuiz)
                    playButtonClickSound()
                }
            )
        }
        item {
            QuizItemCard(
                title = "Present Continuous",
                image = R.drawable.present_continues,
                info = "Berisi 10 soal dengan 3 pilihan ganda",
                onItemClick = {
                    navController.navigate(Destination.PresentContinuousQuiz)
                    playButtonClickSound()
                }
            )
        }
        item {
            QuizItemCard(
                title = "Present Perfect",
                image = R.drawable.present_perfect,
                info = "Berisi 10 soal dengan 3 pilihan ganda",
                onItemClick = {
                    navController.navigate(Destination.PresentPerfectQuiz)
                    playButtonClickSound()
                }
            )
        }
        item {
            QuizItemCard(
                title = "Simple Future",
                image = R.drawable.simple_future,
                info = "Berisi 10 soal dengan 3 pilihan ganda",
                onItemClick = {
                    navController.navigate(Destination.SimpleFutureQuiz)
                    playButtonClickSound()
                }
            )
        }
    }
}


@Preview
@Composable
fun PreviewListQuizScreen() {
    // Dummy NavController for preview
    val navController = rememberNavController()
    ListQuizScreen(navController = navController)
}



