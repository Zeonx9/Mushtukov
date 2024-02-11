package com.example.tinkofffintechlab.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.tinkofffintechlab.domain.model.Film
import com.example.tinkofffintechlab.domain.model.FilmDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {

    @Query("select * from films")
    fun getFilms(): Flow<List<Film>>

    @Query("select * from filmDetails where id = :id")
    suspend fun getFilmDetailsById(id: Int): FilmDetails?

    @Upsert
    suspend fun saveFilmToFavorites(film: Film)

    @Upsert
    suspend fun saveFilmDetails(filmDetails: FilmDetails)

    @Delete
    suspend fun deleteSavedFilm(film: Film)

}