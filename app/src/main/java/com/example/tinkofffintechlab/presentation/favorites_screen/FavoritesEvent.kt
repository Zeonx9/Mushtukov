package com.example.tinkofffintechlab.presentation.favorites_screen

import androidx.navigation.NavController
import com.example.tinkofffintechlab.domain.model.Film

sealed interface FavoritesEvent {
    data class FilmClickEvent(val navController: NavController, val film: Film) : FavoritesEvent
    data class DeleteFromFavoriteEvent(val film: Film) : FavoritesEvent
    data class SearchClickEvent(val navController: NavController) : FavoritesEvent
    data class FilmClickLandscapeEvent(val film: Film) : FavoritesEvent
}
