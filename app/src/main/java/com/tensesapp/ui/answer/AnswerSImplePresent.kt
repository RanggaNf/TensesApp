package com.tensesapp.ui.answer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tensesapp.data.model.EnglishConstants
import com.tensesapp.ui.item.AnswerItem

@Composable
fun AnswerSimplePresent() {
    val questions = EnglishConstants.getSimplePresentQuestions() // Ganti dengan jenis pertanyaan yang diinginkan
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(questions) { question ->
            AnswerItem(question = question)
        }
    }
}


