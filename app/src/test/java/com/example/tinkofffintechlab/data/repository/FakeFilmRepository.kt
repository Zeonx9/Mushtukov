package com.example.tinkofffintechlab.data.repository

import com.example.tinkofffintechlab.domain.model.Film
import com.example.tinkofffintechlab.domain.model.FilmDetails
import com.example.tinkofffintechlab.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class FakeFilmRepository(vararg loadedFilms: Film) : FilmRepository {
    private val films: MutableStateFlow<List<Film>> = MutableStateFlow(loadedFilms.toList())
    override val filmFlow: Flow<List<Film>> = films

    override suspend fun fetchFilms(): List<Film> {
        return films.value
    }

    override suspend fun getFilmDetailsById(id: Int): FilmDetails {
        return FilmDetails(
            id = id,
            name = "undefined",
            posterUrl = null,
            description = "undefined",
            emptyList(),
            emptyList()
        )
    }

    override suspend fun addFilmToFavorites(film: Film, filmDetails: FilmDetails) {
        films.update { oldList ->
            val newList = ArrayList(oldList)
            val index = newList.indexOfFirst { it.id == film.id }
            newList[index] = film.copy(isFavorite = true)
            newList
        }
    }

    override fun getFavoriteFilms(): Flow<List<Film>> {
        return films.map { list -> list.filter { it.isFavorite } }
    }

    override suspend fun deleteFromFavorites(film: Film) {
        films.update { oldList ->
            val newList = ArrayList(oldList)
            val index = newList.indexOfFirst { it.id == film.id }
            newList[index] = film.copy(isFavorite = false)
            newList
        }
    }
}