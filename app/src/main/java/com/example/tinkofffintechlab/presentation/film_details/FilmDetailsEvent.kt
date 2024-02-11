package com.example.tinkofffintechlab.presentation.film_details

import androidx.navigation.NavController

sealed interface FilmDetailsEvent {
    data object ReloadEvent : FilmDetailsEvent
    data class UpEvent(val navController: NavController) : FilmDetailsEvent
}