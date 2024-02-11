package com.example.tinkofffintechlab.presentation.home_page

import androidx.navigation.NavController
import com.example.tinkofffintechlab.domain.model.Film

sealed interface HomeScreenEvent {
    data class FilmClickEvent(val navController: NavController, val film: Film) : HomeScreenEvent
    data object ReloadEvent : HomeScreenEvent
    data class AddToFavoritesEvent(val film: Film) : HomeScreenEvent
    data class SearchClickedEvent(val navController: NavController) : HomeScreenEvent
    data class FilmClickLandscapeEvent(val film: Film) : HomeScreenEvent
}