package com.tensesapp

import ListQuizScreen
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tensesapp.navigation.Actions
import com.tensesapp.navigation.Destination
import com.tensesapp.ui.screen.DetailScreen
import com.tensesapp.ui.screen.HomeScreen
import com.tensesapp.ui.screen.LoginScreen
import com.tensesapp.ui.screen.MateriScreen
import android.annotation.SuppressLint
import android.media.SoundPool
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.tensesapp.R
import com.tensesapp.ui.theme.colorPrimary
import com.tensesapp.ui.theme.ghost_white
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tensesapp.navigation.Destination.OriginScreen
import com.tensesapp.navigation.Destination.PresentContinuousQuiz
import com.tensesapp.navigation.Destination.SimplePastQuiz
import com.tensesapp.navigation.Destination.favoriteScreen
import com.tensesapp.quiz.PresentContinuousQuiz
import com.tensesapp.quiz.PresentPerfectQuiz
import com.tensesapp.quiz.SimpleFutureQuiz
import com.tensesapp.quiz.SimplePastQuiz
import com.tensesapp.quiz.SimplePresentQuiz
import com.tensesapp.ui.answer.AnswerPersentContinuos
import com.tensesapp.ui.answer.AnswerPersentPerfect
import com.tensesapp.ui.answer.AnswerSimpleFuture
import com.tensesapp.ui.answer.AnswerSimplePast
import com.tensesapp.ui.answer.AnswerSimplePresent
import com.tensesapp.ui.screen.AboutScreen
import com.tensesapp.ui.screen.FavoriteScreen
import com.tensesapp.ui.screen.OriginScreen
import com.tensesapp.ui.screen.ProductScreen
import com.tensesapp.ui.screen.onDevelop
import com.tensesapp.ui.theme.Primary


@Composable
fun TensesApp() {
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }

    // State untuk indeks terpilih di bottom bar
    val selectedIndex = remember { mutableStateOf(0) }

    // State untuk menentukan apakah bottom bar dan top bar harus ditampilkan
    val shouldShowBottomBar = remember { mutableStateOf(true) }
    val shouldShowTopBar = remember { mutableStateOf(true) }

    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { controller, destination, _ ->
            shouldShowBottomBar.value = when (destination?.route) {
                Destination.SimplePresentQuiz,
                Destination.SimplePastQuiz,
                Destination.PresentContinuousQuiz,
                Destination.PresentPerfectQuiz,
                Destination.Login,
                Destination.SimpleFutureQuiz,
                Destination.AboutScreen,
                Destination.AnswerSimplePresent,
                Destination.AnswerSimplePast,
                Destination.AnswerSimpleFuture,
                Destination.AnswerPresentPerfect,
                Destination.AnswerPersentContinuos,
                Destination.OriginScreen,
                Destination.onDevelop,
                Destination.favoriteScreen-> false


                else -> true
            }
            shouldShowTopBar.value = shouldShowBottomBar.value
        }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    MaterialTheme {
        Scaffold(
            topBar = {
                if (shouldShowTopBar.value) {
                    CustomTopAppBar()
                }
            },
            bottomBar = {
                if (shouldShowBottomBar.value) {
                    CustomsBottomBar(navController, selectedIndex)
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Destination.Login,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Destination.Login) {
                    LoginScreen(navController = navController)
                }
                composable(Destination.Home) {
                    HomeScreen(navController = navController)
                }
                composable(Destination.Materi) {
                    MateriScreen(navigateToDetail = actions.openDetailMateri)
                }
                composable(
                    route = "${Destination.DetailScreen}/{materiId}",
                    arguments = listOf(navArgument("materiId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val materiId = backStackEntry.arguments?.getInt("materiId")
                    if (materiId != null) {
                        DisposableEffect(Unit) {
                            shouldShowBottomBar.value = false
                            shouldShowTopBar.value =
                                false // Semua bar disembunyikan saat di DetailScreen
                            onDispose {
                                shouldShowBottomBar.value = true // Kembalikan ke nilai semula
                                shouldShowTopBar.value = true // Kembalikan ke nilai semula
                            }
                        }
                        DetailScreen(materiId = materiId, navigateBack = actions.upPress)
                    } else {
                        // Handle jika materiId null atau tidak valid
                    }
                }

                composable(Destination.QuizList) {
                    ListQuizScreen(navController = navController)
                }
                composable(Destination.SimplePresentQuiz) {
                    SimplePresentQuiz(navController = navController)
                }
                composable(Destination.SimplePastQuiz) {
                    SimplePastQuiz(navController = navController)
                }
                composable(Destination.PresentContinuousQuiz) {
                    PresentContinuousQuiz(navController = navController)
                }
                composable(Destination.PresentPerfectQuiz) {
                    PresentPerfectQuiz(navController = navController)
                }
                composable(Destination.SimpleFutureQuiz) {
                    SimpleFutureQuiz(navController = navController)
                }
                composable(Destination.AboutScreen) {
                    AboutScreen(navigateBack = actions.upPress)
                }
                composable(Destination.OriginScreen) {
                    OriginScreen(navigateBack = actions.upPress)
                }
                composable(Destination.ProductScreen) {
                    ProductScreen(navController = navController)
                }
                composable(Destination.AnswerSimplePresent) {
                    AnswerSimplePresent()
                }
                composable(Destination.AnswerSimplePast) {
                    AnswerSimplePast()
                }
                composable(Destination.AnswerSimpleFuture) {
                    AnswerSimpleFuture()
                }
                composable(Destination.AnswerPersentContinuos) {
                    AnswerPersentContinuos()
                }
                composable(Destination.AnswerPresentPerfect) {
                    AnswerPersentPerfect()
                }
                composable(Destination.favoriteScreen) {
                    FavoriteScreen(navigateToDetail= actions.openDetailMateri )
                }
                composable(Destination.onDevelop) {
                    onDevelop(navigateBack = actions.upPress)
                }
            }
        }
    }
}

    @Composable
    fun CustomTopAppBar() {
        TopAppBar(
            elevation = 0.dp,
            modifier = Modifier.fillMaxWidth(),
            title = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Learn Tenses",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White,
                        style = TextStyle(
                            fontStyle = FontStyle.Italic,
                            fontFamily = FontFamily(Font((R.font.josefin_sans_semibold_italic))),
                            fontSize = 22.sp
                        )
                    )
                    IconButton(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        onClick = { }
                    ) {
                    }
                }
            },
            backgroundColor = colorPrimary,
        )
    }


@Composable
fun CustomsBottomBar(navController: NavHostController, selectedIndex: MutableState<Int>) {
    val listItems = listOf("Home", "Materi", "Quiz", "About")
    val context = LocalContext.current

    // Fungsi untuk memainkan suara klik tombol
    fun playButtonClickSound() {
        val soundPool = SoundPool.Builder().setMaxStreams(1).build()
        val buttonClickSoundId = soundPool.load(context, R.raw.tap_notification, 1)
        soundPool.setOnLoadCompleteListener { _, _, _ ->
            soundPool.play(buttonClickSoundId, 1F, 1F, 0, 0, 1F)
        }
    }

    Card(
        elevation = 8.dp, // Tambahkan elevation untuk shadow
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(70.dp)
    ) {
        BottomNavigation(backgroundColor = Primary) { // Atur warna latar belakang
            listItems.forEachIndexed { index, label ->
                val isSelected = selectedIndex.value == index
                BottomNavigationItem(
                    icon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom,
                            modifier = Modifier.padding(top = 12.dp) // Tambahkan padding ke bawah
                        ) {
                            Icon(
                                imageVector = when (index) {
                                    0 -> Icons.Filled.Home
                                    1 -> Icons.Filled.Book
                                    2 -> Icons.Filled.Quiz
                                    3 -> Icons.Filled.AccountBox
                                    else -> Icons.Filled.Error // Handle unexpected index
                                },
                                contentDescription = null,
                                tint = if (isSelected) colorPrimary else Color.Gray
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = label,
                                style = TextStyle(fontSize = 12.sp),
                                color = if (isSelected) colorPrimary else Color.Gray
                            )
                        }
                    },
                    selected = isSelected,
                    onClick = {
                        playButtonClickSound() // Memainkan suara klik tombol
                        selectedIndex.value = index // Update selected index
                        // Navigasi sesuai dengan indeks yang dipilih
                        when (index) {
                            0 -> navController.navigate(Destination.Home)
                            1 -> navController.navigate(Destination.Materi)
                            2 -> navController.navigate(Destination.QuizList)
                            3 -> navController.navigate(Destination.ProductScreen)
                            // Tambahkan navigasi untuk item lain jika diperlukan
                        }
                    },
                    alwaysShowLabel = false
                )
            }
        }
    }
}
