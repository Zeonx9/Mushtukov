package com.example.tinkofffintechlab.data.repository

import android.util.Log
import com.example.tinkofffintechlab.data.remote.api.FilmsApi
import com.example.tinkofffintechlab.domain.dao.FilmDao
import com.example.tinkofffintechlab.domain.model.Film
import com.example.tinkofffintechlab.domain.model.FilmDetails
import com.example.tinkofffintechlab.domain.model.toFilm
import com.example.tinkofffintechlab.domain.model.toFilmDetails
import com.example.tinkofffintechlab.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val filmsApi: FilmsApi,
    private val filmDao: FilmDao
): FilmRepository {

    private val filmStateFlow: MutableStateFlow<List<Film>> = MutableStateFlow(emptyList())
    private val filmDetailsById: MutableMap<Int, FilmDetails> = HashMap()

    override val filmFlow: Flow<List<Film>> = filmStateFlow

    override suspend fun fetchFilms(): List<Film> {
        val filmsFromApi = filmsApi.getTopFilms().films.map { it.toFilm() }
        val favoriteFilms = filmDao.getFilms()
        val combined = combineApiListWithFavorites(filmsFromApi, favoriteFilms.first())

        Log.d(javaClass.name, "network call to fetch films triggered!")

        filmStateFlow.update {
            combined
        }
        return combined
    }

    override suspend fun getFilmDetailsById(id: Int): FilmDetails {
        val savedDetails = filmDao.getFilmDetailsById(id)
        if (savedDetails != null) {
            return savedDetails
        }

        if (!filmDetailsById.containsKey(id)) {
            filmDetailsById[id] = filmsApi.getFilmDetails(id).toFilmDetails()
        }
        return filmDetailsById[id]!!
    }

    override suspend fun addFilmToFavorites(film: Film, filmDetails: FilmDetails) {
        val favoriteFilm = film.copy( isFavorite = true )

        filmDao.saveFilmToFavorites(favoriteFilm)
        filmDao.saveFilmDetails(filmDetails)

        filmStateFlow.update { replaceElementInList(it, favoriteFilm) }
    }

    override fun getFavoriteFilms(): Flow<List<Film>> {
        return filmDao.getFilms()
    }

    override suspend fun deleteFromFavorites(film: Film) {
        val notFavoriteFilm = film.copy(isFavorite = false)
        filmDao.deleteSavedFilm(notFavoriteFilm)

        filmStateFlow.update { replaceElementInList(it, notFavoriteFilm) }
    }

    private fun replaceElementInList(oldList: List<Film>, newValue: Film): List<Film> {
        val newList = ArrayList(oldList)
        val index = newList.indexOfFirst { it.id == newValue.id }
        newList[index] = newValue
        return newList
    }

    private fun combineApiListWithFavorites(apiList: List<Film>, favoriteList: List<Film>): List<Film> {
        val newList = ArrayList(apiList)
        val indexById = newList.withIndex().associateBy ({ it.value.id }) {it.index}
        for (film in favoriteList) {
            if (indexById.containsKey(film.id)) {
                newList[indexById[film.id]!!] = film
            }
        }
        return newList
    }
}