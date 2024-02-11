package com.example.tinkofffintechlab.presentation.favorites_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tinkofffintechlab.R
import com.example.tinkofffintechlab.presentation.common.AdaptiveFilmList
import com.example.tinkofffintechlab.presentation.common.EmptyListPlaceholder
import com.example.tinkofffintechlab.presentation.common.BottomNavBar
import com.example.tinkofffintechlab.presentation.common.FilmList
import com.example.tinkofffintechlab.presentation.common.FilmListItem
import com.example.tinkofffintechlab.presentation.common.TopBar
import com.example.tinkofffintechlab.presentation.home_page.HomeScreenEvent

@Composable
fun FavoritesScreen(
    navController: NavController,
    state: FavoritesState,
    onEvent: (FavoritesEvent) -> Unit
) {
    Scaffold (
        topBar = {
            TopBar(title = stringResource(R.string.favorites_screen_title)) {
                onEvent(FavoritesEvent.SearchClickEvent(navController))
            }
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { paddingValues ->
        if (state.films.isEmpty()) {
            EmptyListPlaceholder(textResId = R.string.no_favorites_message)
        }
        else {
            AdaptiveFilmList(
                films = state.films,
                filmDetails = state.filmDetails,
                onLongPress = {
                    onEvent(FavoritesEvent.DeleteFromFavoriteEvent(it))
                },
                onClickPortrait = {
                    onEvent(FavoritesEvent.FilmClickEvent(navController, it))
                },
                onClickLandscape = {
                    onEvent(FavoritesEvent.FilmClickLandscapeEvent(it))
                },
                paddingValues = paddingValues
            )
        }
    }
}