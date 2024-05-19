package com.tensesapp.ui.screen

import android.content.Context
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tensesapp.R
import com.tensesapp.data.di.Injection
import com.tensesapp.data.model.Materi
import com.tensesapp.ui.common.UiState
import com.tensesapp.ui.item.EmptyList
import com.tensesapp.ui.item.ItemMateri
import com.tensesapp.ui.viewmodel.MateriViewModel
import com.tensesapp.ui.viewmodel.ViewModelFactory


@Composable
fun MateriScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: MateriViewModel = viewModel(
        factory = ViewModelFactory(
            repository = Injection.materiRepository(context),
            context = context
        )
    ),
    navigateToDetail: (Int) -> Unit,
) {
    val queryState = viewModel.query.collectAsState()
    val uiState = viewModel.uiState.collectAsState(initial = UiState.Loading)

    MateriContent(
        query = queryState.value,
        onQueryChange = viewModel::search,
        listMateri = when (val state = uiState.value) {
            is UiState.Loading -> emptyList()
            is UiState.Success -> state.data
            is UiState.Error -> emptyList()
        },
        onFavoriteIconClick = { id, newState ->
            viewModel.updateMateri(id, newState)
        },
        onItemClick = navigateToDetail
    )

    // Trigger search when screen loads
    if (uiState.value is UiState.Loading) {
        viewModel.search(queryState.value)
    }
}
// Fungsi Composable untuk menampilkan konten materi (MateriContent)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MateriContent(
    query: String,
    onQueryChange: (String) -> Unit,
    listMateri: List<Materi>,
    onFavoriteIconClick: (id: Int, newState: Boolean) -> Unit,
    onItemClick: (Int) -> Unit, // Menambahkan parameter onItemClick
    modifier: Modifier = Modifier
) {
    // Column digunakan untuk menyusun elemen secara vertikal
    Column {
        // Memanggil fungsi SearchBar untuk menampilkan bar pencarian
        SearchBar(
            query = query,
            onQueryChange = onQueryChange,
            onSearch = {},
            active = false,
            onActiveChange = {},
            leadingIcon = {
                // Menampilkan ikon pencarian
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            placeholder = {
                // Menampilkan teks placeholder pada SearchBar
                Text(stringResource(R.string.search_materi))
            },
            shape = MaterialTheme.shapes.large,
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
                .heightIn(min = 48.dp)

        ) {
        }
        // Memeriksa apakah daftar materi tidak kosong
        if (listMateri.isNotEmpty()) {
            // Memanggil fungsi ListMateri untuk menampilkan daftar materi
            ListMateri(
                listMateri = listMateri,
                onFavoriteIconClick = onFavoriteIconClick,
                onItemClick = onItemClick // Memasukkan parameter onItemClick
            )
        } else {
            // Jika daftar materi kosong, tampilkan komponen EmptyList
            EmptyList(
                warning = stringResource(R.string.empty_data),
            )
        }
    }
}

// Fungsi Composable untuk menampilkan daftar materi (ListMateri)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListMateri(
    listMateri: List<Materi>,
    onFavoriteIconClick: (id: Int, newState: Boolean) -> Unit,
    onItemClick: (Int) -> Unit,
    contentPaddingTop: Dp = 0.dp
) {
    LazyColumn(
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            bottom = 16.dp,
            top = contentPaddingTop
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(listMateri, key = { it.id }) { materi ->
            ItemMateri(
                materi = materi,
                onItemClick = { onItemClick(materi.id) },
                onFavoriteIconClick = { onFavoriteIconClick(materi.id, !materi.isFavorite) },
                modifier = Modifier
                    .animateItemPlacement(tween(durationMillis = 200))
            )
        }
    }
}





