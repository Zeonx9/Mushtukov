package com.example.tinkofffintechlab.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tinkofffintechlab.data.remote.dto.FilmDto

@Entity(tableName = "films")
data class Film(
    @PrimaryKey
    val id: Int,
    val name: String,
    val year: String,
    val posterUrl: String?,
    val genre: String?,
    val isFavorite: Boolean = false
)

fun FilmDto.toFilm(): Film {
    return Film(
        id = filmId,
        name = nameRu ?: "",
        year = year,
        posterUrl = posterUrl,
        genre = genres.first().genre
    )
}