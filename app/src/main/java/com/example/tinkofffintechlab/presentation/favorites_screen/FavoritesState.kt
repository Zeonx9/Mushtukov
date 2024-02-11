package com.example.tinkofffintechlab.presentation.favorites_screen

import com.example.tinkofffintechlab.domain.model.Film
import com.example.tinkofffintechlab.domain.model.FilmDetails

data class FavoritesState (
    val films: List<Film> = emptyList(),
    val filmDetails: FilmDetails? = null
)
