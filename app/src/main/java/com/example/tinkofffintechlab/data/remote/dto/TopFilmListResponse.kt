package com.example.tinkofffintechlab.data.remote.dto

data class TopFilmListResponse(
    val films: List<FilmDto>,
    val pagesCount: Int
)