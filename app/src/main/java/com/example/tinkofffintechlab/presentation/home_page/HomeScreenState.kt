package com.example.tinkofffintechlab.presentation.home_page

import com.example.tinkofffintechlab.common.Resource
import com.example.tinkofffintechlab.domain.model.Film
import com.example.tinkofffintechlab.domain.model.FilmDetails

data class HomeScreenState(
    val films: List<Film> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
    val filmDetails: FilmDetails? = null
)
