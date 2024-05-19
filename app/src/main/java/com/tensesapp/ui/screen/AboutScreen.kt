package com.tensesapp.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tensesapp.ui.theme.colorPrimary

@Composable
fun AboutScreen(navigateBack: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopAppBar(
                title = { Text(text = "Tentang aplikasi") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                backgroundColor = colorPrimary
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                item {
                    Text(
                        text = "ABOUT",
                        style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    Text(
                        text = "(TENTANG APLIKASI)",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    DescriptionText(
                        text = "The Learn Tenses application is developed to fulfill the requirements of both students and teachers in teaching English grammar skills at the senior high school level. The Tenses are the most important part of the English Language. If you wish to write a correct sentence or say anything to anyone, you need to express the idea in the right tense form. The English language has three main time divisions- Past, Present, and Future expressed by the tenses. This application can assist the students in learning tenses easily. The target is the students in senior high school.",
                        modifier = Modifier.padding(bottom = 24.dp),
                        italic = true // membuat teks bahasa Inggris menjadi miring
                    )
                }
                item {
                    DescriptionText(
                        text = "APLIKASI Learn Tenses dibuat untuk memenuhi kebutuhan baik siswa maupun guru dalam pembelajaran keterampilan tata bahasa Inggris di tingkat sekolah menengah atas. Tenses adalah bagian paling penting dari Bahasa Inggris. Jika Anda ingin menulis kalimat yang benar atau mengatakan sesuatu kepada seseorang, Anda perlu menyampaikan ide dalam bentuk waktu yang tepat. Bahasa Inggris memiliki tiga pembagian waktu utama - Masa Lampau, Masa Sekarang, dan Masa Depan yang dinyatakan melalui tenses. Aplikasi ini dapat membantu siswa untuk belajar tenses dengan mudah. Target pengguna aplikasi ini adalah siswa di sekolah menengah pertama dan sekolah menengah atas.",
                        modifier = Modifier.padding(bottom = 24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun DescriptionText(text: String, modifier: Modifier = Modifier, italic: Boolean = false) {
    val paragraphStyle = ParagraphStyle(textAlign = TextAlign.Justify)
    Text(
        text = AnnotatedString(text = text, paragraphStyle = paragraphStyle),
        style = MaterialTheme.typography.body2.copy(fontStyle = if (italic) FontStyle.Italic else FontStyle.Normal),
        modifier = modifier.padding(horizontal = 16.dp)
    )
}

@Preview
@Composable
fun PreviewAboutScreen() {
    AboutScreen(navigateBack = {})
}
