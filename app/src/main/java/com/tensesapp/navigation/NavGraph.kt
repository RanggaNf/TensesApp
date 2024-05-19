

package com.tensesapp.navigation

import androidx.navigation.NavHostController

object Destination {
    // Tambahkan konstanta untuk home screen
    const val Home = "Home"
    const val Splash = "Splash"
    const val Login = "Login"
    const val DetailScreen = "DetailScreen"
    const val Materi = "Materi"
    const val QuizList = "QuizList"
    const val SimplePresentQuiz = "SimplePresentQuiz"
    const val SimplePastQuiz = "SimplePastQuiz"
    const val PresentContinuousQuiz = "PresentContinuousQuiz"
    const val PresentPerfectQuiz = "PresentPerfectQuiz"
    const val SimpleFutureQuiz = "SimpleFutureQuiz"
    const val AboutScreen = "AboutScreen"
    const val ProductScreen = "Product Screen"
    const val OriginScreen = "OriginScreen"
    const val AnswerSimplePresent = "AnswerSimplePresent"
    const val AnswerSimplePast = "AnswerSimplePast"
    const val AnswerSimpleFuture = "AnswerSimpleFuture"
    const val AnswerPresentPerfect = "AnswerPresentPerfect"
    const val AnswerPersentContinuos = "AnswerPersentContinuos"
    const val favoriteScreen ="favoriteScreen"
    const val onDevelop = "onDevelop"
}

class Actions(navController: NavHostController) {

    // Fungsi untuk membuka layar detail materi
    val openDetailMateri: (Int) -> Unit = { materiId ->
        navController.navigate("${Destination.DetailScreen}/$materiId")
    }

    // Fungsi untuk kembali ke layar sebelumnya
    val upPress: () -> Unit = {
        navController.popBackStack()
    }
}

