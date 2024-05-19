package com.tensesapp.ui.screen

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tensesapp.data.model.Materi
import com.tensesapp.ui.common.UiState
import com.tensesapp.ui.viewmodel.FavoriteViewModel
import com.tensesapp.ui.viewmodel.ViewModelFactory
import com.tensesapp.R
import com.tensesapp.data.di.Injection
import com.tensesapp.ui.item.EmptyList

@Composable
fun FavoriteScreen(
    navigateToDetail: (Int) -> Unit,
    context: Context = LocalContext.current,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(Injection.materiRepository(context), context)
    )
) {
    val uiState = viewModel.uiState.collectAsState(initial = UiState.Loading)

    FavoriteInfo(
        listMateri = when (val state = uiState.value) {
            is UiState.Loading -> emptyList()
            is UiState.Success -> state.data
            is UiState.Error -> emptyList()
        },
        navigateToDetail = navigateToDetail,
        onFavoriteIconClick = { id, newState ->
            viewModel.updatePlayer(id, newState)
        }
    )

    // Trigger get favorites when screen loads
    if (uiState.value is UiState.Loading) {
        viewModel.getFavoriteMateri()
    }
}

@Composable
fun FavoriteInfo(
    listMateri: List<Materi>,
    navigateToDetail: (Int) -> Unit,
    onFavoriteIconClick: (id: Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        if (listMateri.isNotEmpty()) {
            ListMateri(
                listMateri = listMateri,
                onFavoriteIconClick = onFavoriteIconClick,
                contentPaddingTop = 16.dp,
                onItemClick = navigateToDetail
            )
        } else {
            EmptyList(
                warning = stringResource(R.string.empty_favorite)
            )
        }
    }
}

