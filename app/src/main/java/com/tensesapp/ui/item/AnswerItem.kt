package com.tensesapp.ui.item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tensesapp.data.model.EnglishQuestion

@Composable
fun AnswerItem(question: EnglishQuestion) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Question ${question.id}: ${question.question}\nAnswer: ${question.optionThree.first}", // Menampilkan nomor pertanyaan, teks pertanyaan, dan jawaban yang benar
            color = Color.Black
        )
    }
}