//package com.tensesapp.ui.screen
//
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import com.tensesapp.data.model.Materi
//
//@Composable
//fun BookmarkScreen(bookmarkedFlowers: List<Materi>) {
//    LazyColumn(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        items(bookmarkedFlowers) { flower ->
//            FlowerCard(flower, isBookmarked = true, onBookmarkToggle = {}) // Tidak perlu toggle di screen bookmark
//        }
//    }
//}
