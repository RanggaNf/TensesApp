package com.tensesapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tensesapp.data.model.Materi
import com.tensesapp.data.repos.MateriRepository
import com.tensesapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: MateriRepository,
    private val context: Context
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Materi>>> =
        MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<List<Materi>>> get() = _uiState

    init {
        getFavoriteMateri()
    }

    fun getFavoriteMateri() = viewModelScope.launch {
        repository.getFavoriteMateri()
            .catch {
                _uiState.value = UiState.Error(it.message.toString())
            }
            .collect {
                _uiState.value = UiState.Success(it)
            }
    }

    fun updatePlayer(id: Int, newState: Boolean) = viewModelScope.launch {
        repository.updateMateri(id, newState)
            .collect { isUpdated ->
                if (isUpdated) {
                    getFavoriteMateri()
                    saveFavoritesToSharedPreferences(id, newState)
                }
            }
    }

    private fun saveFavoritesToSharedPreferences(id: Int, newState: Boolean) {
        val sharedPreferences =
            context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(id.toString(), newState)
        editor.apply()
    }
}
