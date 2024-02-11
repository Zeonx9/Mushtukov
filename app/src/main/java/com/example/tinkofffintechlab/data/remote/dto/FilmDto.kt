package com.example.tinkofffintechlab.data.remote.dto

import com.example.tinkofffintechlab.domain.model.Country
import com.example.tinkofffintechlab.domain.model.Genre

data class FilmDto(
    val countries: List<Country>,
    val filmId: Int,
    val filmLength: String,
    val genres: List<Genre>,
    val isAfisha: Int,
    val isRatingUp: Any,
    val nameEn: String?,
    val nameRu: String?,
    val posterUrl: String?,
    val posterUrlPreview: String?,
    val rating: String,
    val ratingChange: Any,
    val ratingVoteCount: Int,
    val year: String
)