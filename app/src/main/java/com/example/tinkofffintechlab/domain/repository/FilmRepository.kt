package com.example.tinkofffintechlab.domain.repository

import com.example.tinkofffintechlab.domain.model.Film
import com.example.tinkofffintechlab.domain.model.FilmDetails
import kotlinx.coroutines.flow.Flow

interface FilmRepository {

    val filmFlow: Flow<List<Film>>

    suspend fun fetchFilms(): List<Film>

    suspend fun getFilmDetailsById(id: Int): FilmDetails

    suspend fun addFilmToFavorites(film: Film, filmDetails: FilmDetails)

    fun getFavoriteFilms(): Flow<List<Film>>

    suspend fun deleteFromFavorites(film: Film)

}