package com.example.tinkofffintechlab.presentation.search_screen

import androidx.navigation.NavController
import com.example.tinkofffintechlab.domain.model.Film

sealed interface SearchEvent {
    data class QueryChangedEvent(val newQuery: String) : SearchEvent
    data class IsActiveChangedEvent(val isActive: Boolean) : SearchEvent
    data class UpEvent(val navController: NavController) : SearchEvent
    data object PreformSearchEvent : SearchEvent
    data class FilmClickEvent(val navController: NavController, val film: Film) : SearchEvent
}