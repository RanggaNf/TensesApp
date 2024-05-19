package com.tensesapp.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tensesapp.data.model.EnglishConstants
import com.tensesapp.data.model.EnglishQuestion
import android.app.Activity
import android.media.MediaPlayer
import android.media.SoundPool
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.tensesapp.R
import com.tensesapp.ui.theme.MainColor
import com.tensesapp.ui.theme.SecondColor
import com.tensesapp.ui.theme.SecondaryVariant
import com.tensesapp.ui.theme.TopColor
import com.tensesapp.R.font
import com.tensesapp.navigation.Destination
import com.tensesapp.ui.item.OptionItem
import com.tensesapp.ui.item.ScoreButton
import com.tensesapp.ui.item.TopProgressBar
import com.tensesapp.ui.item.WantedStars
import com.tensesapp.ui.screen.WelcomeScreen

val fontCompose = FontFamily(Font(font.lato))
val fontColor = Color.White.copy(0.8f)

@Composable
fun SimplePresentQuiz(
    navController: NavController
) {
    val context = LocalContext.current

    // Fungsi untuk memainkan suara klik tombol
    fun playButtonClickSound() {
        val soundPool = SoundPool.Builder().setMaxStreams(1).build()
        val buttonClickSoundId = soundPool.load(context, R.raw.clcik_materi, 1)
        soundPool.setOnLoadCompleteListener { _, _, _ ->
            soundPool.play(buttonClickSoundId, 1F, 1F, 0, 0, 1F)
        }
    }

    DisposableEffect(Unit) {
        val mediaPlayer = MediaPlayer.create(context, R.raw.music_quiz)
        mediaPlayer.isLooping = true
        mediaPlayer.start()

        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }

    var isClick by remember { mutableStateOf(false) }
    var isDone by remember { mutableStateOf(false) }
    var isDown by remember { mutableStateOf(true) }
    var isPlaying by remember { mutableStateOf(false) }
    var currentPosition by remember {
        mutableStateOf(if (isDone) 1 else 1)
    }
    val questionsArray = EnglishConstants.getSimplePresentQuestions()
    val question: EnglishQuestion = questionsArray[currentPosition - 1]
    var score by remember {
        mutableStateOf(if (isDone) 0 else 0)
    }
    var currentProgress by remember {
        mutableStateOf(if (isDone) 1f else 0.1f)
    }
    var selectedIndex by remember { mutableStateOf(-1) }
    isClick = selectedIndex != -1
    val onItemClick = { index: Int ->
        selectedIndex = index
    }
    val buttonText = if (currentPosition == 10) "Finish" else "Next"
    val buttonTextFirst = if (isPlaying) {
        "Restart"
    } else {
        "Start"
    }
    val buttonTextSecond = if (isPlaying) {
        if (isDone) {
            "Exit"
        } else {
            "Resume"
        }
    } else {
        "Exit"
    }
    val buttonTextThird = if (isDone) {
        "Answer Key"
    } else {
        ""
    }
    val modifierOne = if (isDown) {
        Modifier
            .padding(top = 0.dp, bottom = 10.dp, end = 10.dp, start = 10.dp)
            .background(TopColor, RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            .fillMaxSize()
    } else {
        Modifier
            .padding(top = 0.dp, bottom = 0.dp, end = 10.dp, start = 10.dp)
            .background(TopColor, RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            .fillMaxWidth()
    }
    val scoreText = when (score) {
        10 -> "Wow! You're on fire."
        in 7..9 -> "Excellent!"
        in 5..6 -> "Great!"
        in 3..4 -> "Not bad."
        in 1..2 -> "Keep practicing."
        else -> "Better luck next time."
    }
    val topText = if (isDone) {
        ""
    } else {
        "TAKE A QUICK QUIZ"
    }

    val activity = (LocalContext.current as? Activity)
    var isBackBtnVisible by remember { mutableStateOf(false) }

    AnimatedVisibility(visible = isBackBtnVisible) {
        AlertDialog(
            onDismissRequest = { },
            buttons = {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = {
                            playButtonClickSound()
                            activity?.onBackPressed()
                        },
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, top = 0.dp, bottom = 10.dp)
                            .fillMaxWidth()
                            .alpha(1f)
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = SecondColor
                        ),
                        shape = RoundedCornerShape(10.dp),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 4.dp,
                            pressedElevation = 6.dp
                        )
                    ) {
                        Text(
                            text = "Exit",
                            fontSize = 14.sp,
                            color = SecondaryVariant,
                            fontWeight = FontWeight.Normal,
                            fontFamily = fontCompose
                        )
                    }
                }
            },
            title = {},
            text = {
                Column(
                    modifier = Modifier
                        .padding(top = 0.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Box(
                        modifier = Modifier
                            .padding(bottom = 0.dp)
                            .clip(RoundedCornerShape(90.dp))
                            .clickable {
                                isBackBtnVisible = false
                            },
                        contentAlignment = Alignment.TopStart
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = "",
                            tint = fontColor,
                            modifier = Modifier
                        )
                    }
                    Text(
                        text = "Are you sure you want to exit Quiz?",
                        color = fontColor,
                        fontWeight = FontWeight.Normal,
                        fontSize = 22.sp,
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            },
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .fillMaxWidth(),
            backgroundColor = MainColor,
            shape = RoundedCornerShape(20.dp),
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = isDown,
            enter = expandVertically { 1 },
            exit = shrinkVertically { 0 }
        ) {
            Column(
                modifier = modifierOne,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WelcomeScreen(
                    scoreText = scoreText,
                    isDone = isDone,
                    score = score,
                    isVisible = !isDone,
                    topText = topText,
                    buttonTextFirst = buttonTextFirst,
                    buttonTextSecond = buttonTextSecond,
                    buttonTextThird = buttonTextThird,
                    onClickStart = {
                        playButtonClickSound()
                        if (!isPlaying) {
                            isDown = !isDown
                            isDone = false
                        } else {
                            currentPosition = 1
                            selectedIndex = -1
                            currentProgress = 0.1f
                            score = 0
                            isDown = false
                            isDone = false
                            isPlaying = false
                        }
                    },
                    onClickSecond = {
                        playButtonClickSound()
                        if (buttonTextSecond == "Exit") {
                            isBackBtnVisible = true
                        } else {
                            isDown = false
                        }
                    },
                    onClickThird = {navController.navigate(Destination.AnswerSimplePresent)

                    }
                )
            }
        }

        AnimatedVisibility(
            visible = !isDown,
            enter = fadeIn(),
            exit = fadeOut()
        )

        {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.TopStart
                    ) {
                        IconButton(
                            onClick = {
                                playButtonClickSound()
                                isBackBtnVisible = true
                            },
                            modifier = Modifier
                                .padding(start = 20.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                contentDescription = "Back icon",
                                tint = Color.White,
                                modifier = Modifier
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        ScoreButton(score.toString()) {}
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        IconButton(
                            onClick = {
                                playButtonClickSound()
                                isDown = !isDown
                            },
                            modifier = Modifier
                                .padding(end = 20.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Menu,
                                contentDescription = "Menu icon",
                                tint = Color.White,
                                modifier = Modifier
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    WantedStars(
                        paddingValues = PaddingValues(top = 0.dp, bottom = 0.dp),
                        count = score,
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .shadow(elevation = 80.dp, RoundedCornerShape(20.dp))
                        .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                        .fillMaxSize()
                        .background(TopColor, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                ) {
                    Row(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TopProgressBar(currentProgress)
                    }
                    Image(
                        painter = painterResource(id = question.image),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
                            .width(300.dp)
                            .height(200.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = "${currentPosition}. ${question.question}",
                        color = fontColor,
                        fontSize = 22.sp,
                        fontFamily = fontCompose,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(
                            top = 20.dp,
                            start = 20.dp,
                            end = 20.dp,
                            bottom = 10.dp
                        ),
                        maxLines = 2
                    )
                    Column(
                        modifier = Modifier
                            .padding(top = 0.dp, bottom = 0.dp)
                            .fillMaxWidth()
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 10.dp)
                                .fillMaxWidth()
                                .clickable {},
                        ) {
                            items(3) { index -> // Limit the loop to 3 iterations
                                val text = when (index) {
                                    0 -> {
                                        question.optionOne.first
                                    }
                                    1 -> {
                                        question.optionTwo.first
                                    }
                                    2 -> {
                                        question.optionThree.first
                                    }
                                    else -> {
                                        ""
                                    }
                                }
                                OptionItem(
                                    text,
                                    index = index,
                                    selected = selectedIndex == index,
                                    onClick = onItemClick,
                                )
                            }
                        }
                        Button(
                            onClick = {
                                playButtonClickSound()
                                if (currentPosition > 0) {
                                    isPlaying = true
                                }
                                if (currentPosition < 10) {
                                    if (selectedIndex + 1 == question.correctAnswer) {
                                        currentProgress += 0.1f
                                        currentPosition++
                                        score++
                                        selectedIndex = -1
                                    } else {
                                        currentProgress += 0.1f
                                        currentPosition++
                                        selectedIndex = -1
                                    }
                                } else {
                                    if (selectedIndex + 1 == question.correctAnswer) {
                                        currentProgress = 1f
                                        score++
                                        isDone = true
                                        isDown = true
                                    } else {
                                        currentProgress = 1f
                                        isDone = true
                                        isDown = true
                                    }
                                }
                            },
                            modifier = Modifier
                                .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
                                .fillMaxWidth()
                                .alpha(1f),
                            enabled = isClick,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = SecondColor
                            ),
                            shape = RoundedCornerShape(10.dp),
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 4.dp,
                                pressedElevation = 6.dp
                            )
                        ) {
                            Text(
                                text = buttonText,
                                fontSize = 14.sp,
                                color = SecondaryVariant,
                                fontWeight = FontWeight.Normal,
                                fontFamily = fontCompose
                            )
                        }
                    }
                }
            }
        }
    }
}
