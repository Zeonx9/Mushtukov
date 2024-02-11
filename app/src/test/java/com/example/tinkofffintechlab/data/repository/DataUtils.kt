package com.example.tinkofffintechlab.data.repository

import com.example.tinkofffintechlab.domain.model.Film


fun testFilm(id: Int = 1, isFavorite: Boolean = false): Film {
    return Film(
        id,
        "name",
        "2000",
        "url",
        "dramma",
        isFavorite
    )
}