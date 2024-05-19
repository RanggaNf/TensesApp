package com.tensesapp.data.model

data class EnglishQuestion(
    val id: Int,
    val question: String,
    val image: Int,
    val optionOne: Pair<String, Int>,
    val optionTwo: Pair<String, Int>,
    val optionThree: Pair<String, Int>,
    val correctAnswer: Int
)
