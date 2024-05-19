package com.tensesapp.data.model

data class Materi(
    val id: Int,
    val name: String,
    val meaning: String,
    val image: Int,
    val definition : String,
    val formula_verb : Int,
    val example : String,
    val formula_nonverb : Int,
    var isFavorite: Boolean = false
)
