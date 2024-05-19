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
fun OriginScreen(navigateBack: () -> Unit) {
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "ORIGINALITY PRODUCT",
                    style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "(ORISINALITAS PRODUK)",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center
                )
                DescriptionText(
                    text = "We assert the authenticity and ownership of this application, which is a product of creative efforts and development by Aldy Muria Rahmaddian. It is protected by copyright law, granting us exclusive rights to reproduction, distribution, and public performance. Any replication, duplication, or unauthorized use of this application, whether in part or in whole, constitutes a violation of our intellectual property rights. We are committed to vigorously protecting our copyright and taking legal action against any infringement or unauthorized use.",
                    modifier = Modifier.padding(bottom = 24.dp),
                    italic = true
                )
                DescriptionText(
                    text = "Kami menegaskan keaslian dan kepemilikan aplikasi ini, yang merupakan produk dari upaya kreatif dan pengembangan oleh Aldy Muria Rahmaddian. Ini dilindungi oleh hukum hak cipta, memberikan kami hak eksklusif untuk reproduksi, distribusi, dan pertunjukan publik. Setiap replikasi, duplikasi, atau penggunaan yang tidak sah dari aplikasi ini, baik sebagian maupun seluruhnya, merupakan pelanggaran terhadap hak kekayaan intelektual kami. Kami berkomitmen untuk melindungi hak cipta kami dengan tegas dan mengambil tindakan hukum terhadap setiap pelanggaran atau penggunaan yang tidak sah.",
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
        }
    }
}



@Preview
@Composable
fun OriginScreenPrwview() {
    OriginScreen(navigateBack = {})
}

