package com.example.tinkofffintechlab.presentation

sealed class Screen(val route: String) {
    data object HomeScreen : Screen(route = "home_screen")
    data object FilmDetailsScreen : Screen(route = "film_details")
    data object FavoritesScreen : Screen(route = "favorites_screen")
    data object SearchScreen : Screen(route = "search_screen")

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}