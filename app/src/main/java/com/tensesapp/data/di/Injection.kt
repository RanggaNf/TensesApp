package com.tensesapp.data.di

import android.content.Context
import com.tensesapp.data.repos.MateriRepository
object Injection {
    // Fungsi untuk mendapatkan instance PresRepository
    fun materiRepository(context: Context): MateriRepository {
        return MateriRepository.getInstance(context)
    }
}
