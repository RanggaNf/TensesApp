package com.tensesapp.data.repos

import android.content.Context
import com.tensesapp.data.model.Materi
import com.tensesapp.data.model.MateriModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class MateriRepository(private val context: Context) {

    private val dummyMateri = MateriModel.list.toMutableList()

    fun getMateriById(id: Int): Materi {
        return dummyMateri.first { it.id == id }
    }

    fun getFavoriteMateri(): Flow<List<Materi>> {
        return flowOf(loadMateriWithFavoriteStatus().filter { it.isFavorite })
    }

    fun searchMateri(query: String): Flow<List<Materi>> {
        val data = loadMateriWithFavoriteStatus().filter {
            it.name.contains(query, ignoreCase = true)
        }
        return flowOf(data)
    }

    fun updateMateri(id: Int, newState: Boolean): Flow<Boolean> {
        val index = dummyMateri.indexOfFirst { it.id == id }
        val result = if (index >= 0) {
            dummyMateri[index].isFavorite = newState
            saveFavoriteToSharedPreferences(id, newState)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    private fun loadMateriWithFavoriteStatus(): List<Materi> {
        return dummyMateri.map { materi ->
            val isFavorite = isMateriFavorite(materi.id)
            materi.copy(isFavorite = isFavorite)
        }
    }

    private fun saveFavoriteToSharedPreferences(id: Int, newState: Boolean) {
        val sharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(id.toString(), newState)
        editor.apply()
    }

    private fun isMateriFavorite(id: Int): Boolean {
        val sharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(id.toString(), false)
    }


    companion object {
        @Volatile
        private var INSTANCE: MateriRepository? = null

        fun getInstance(context: Context): MateriRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: MateriRepository(context).also { INSTANCE = it }
            }
        }
    }
}
