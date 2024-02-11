package com.example.tinkofffintechlab.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tinkofffintechlab.data.remote.dto.FilmDetailsDto

@Entity(tableName = "filmDetails")
data class FilmDetails(
    @PrimaryKey
    val id: Int,
    val name: String,
    val posterUrl: String?,
    val description: String,
    val genres: List<Genre>,
    val countries: List<Country>,
)

fun FilmDetailsDto.toFilmDetails(): FilmDetails {
    return FilmDetails(
        id = kinopoiskId,
        name = nameRu,
        posterUrl = posterUrl,
        description = description,
        genres = genres,
        countries = countries
    )
}


