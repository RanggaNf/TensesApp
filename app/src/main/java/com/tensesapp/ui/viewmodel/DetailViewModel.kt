package com.tensesapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tensesapp.data.model.Materi
import com.tensesapp.data.repos.MateriRepository
import com.tensesapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(

    private val repository: MateriRepository,
    private val context: Context
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<Materi>> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<Materi>> get() = _uiState

    fun getMateriById(id: Int) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        _uiState.value = UiState.Success(repository.getMateriById(id))
    }

    fun updateMateri(id: Int, newState: Boolean) = viewModelScope.launch {
        repository.updateMateri(id, !newState)
            .collect { isUpdated ->
                if (isUpdated) {
                    getMateriById(id)
                    saveFavoritesToSharedPreferences(id, !newState)
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

