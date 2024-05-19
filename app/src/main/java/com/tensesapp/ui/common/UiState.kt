package com.tensesapp.ui.common

// Sealed class untuk merepresentasikan berbagai state UI
sealed class UiState<out T : Any?> {

    // State untuk menunjukkan bahwa data sedang dimuat
    object Loading : UiState<Nothing>()

    // State untuk menunjukkan bahwa data berhasil dimuat
    data class Success<out T : Any>(val data: T) : UiState<T>()

    // State untuk menunjukkan bahwa terjadi kesalahan saat memuat data
    data class Error(val message: String) : UiState<Nothing>()
}