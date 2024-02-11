package com.example.tinkofffintechlab.presentation.search_screen

import com.example.tinkofffintechlab.domain.model.Film

data class SearchScreenState(
    val query: String = "",
    val isActive: Boolean = false,
    val results: List<Film> = emptyList()
)
