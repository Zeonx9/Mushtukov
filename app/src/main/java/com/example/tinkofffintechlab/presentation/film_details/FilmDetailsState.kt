package com.example.tinkofffintechlab.presentation.film_details

import com.example.tinkofffintechlab.domain.model.FilmDetails

data class FilmDetailsState (
    val filmDetails: FilmDetails? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
