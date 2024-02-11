package com.example.tinkofffintechlab.presentation.home_page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tinkofffintechlab.R
import com.example.tinkofffintechlab.domain.model.Film
import com.example.tinkofffintechlab.presentation.common.AdaptiveFilmList
import com.example.tinkofffintechlab.presentation.common.BottomNavBar
import com.example.tinkofffintechlab.presentation.common.FilmDetailsComponent
import com.example.tinkofffintechlab.presentation.common.FilmList
import com.example.tinkofffintechlab.presentation.common.LoadingBar
import com.example.tinkofffintechlab.presentation.common.NetworkError
import com.example.tinkofffintechlab.presentation.common.TopBar

@Composable
fun HomeScreen(
    navController: NavController,
    state: HomeScreenState,
    onEvent: (HomeScreenEvent) -> Unit
) {
    Scaffold (
        topBar = {
            TopBar(title = stringResource(R.string.home_screen_title)) {
                onEvent(HomeScreenEvent.SearchClickedEvent(navController))
            }
        },
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { paddingValues ->
        if (state.isLoading) {
            LoadingBar()
        }
        else if (state.error.isNotBlank()) {
            NetworkError(message = stringResource(R.string.network_error_message)) {
                onEvent(HomeScreenEvent.ReloadEvent)
            }
        }
        else {
            AdaptiveFilmList(
                films = state.films,
                filmDetails = state.filmDetails,
                onLongPress = {
                    onEvent(HomeScreenEvent.AddToFavoritesEvent(it))
                },
                onClickPortrait = {
                    onEvent(HomeScreenEvent.FilmClickEvent(navController, it))
                },
                onClickLandscape = {
                    onEvent(HomeScreenEvent.FilmClickLandscapeEvent(it))
                },
                paddingValues = paddingValues
            )
        }
    }
}

@Composable
@Preview
fun PreviewHomeScreen() {
    HomeScreen(
        navController = rememberNavController(),
        state = HomeScreenState(
            listOf(
                Film(0,
                    "Мастер",
                    "2024",
                    "url",
                     "драмма"
                ),
                Film(0,
                    "Маргарита",
                    "2022",
                    "url",
                    "боевик"
                )
            )
        ),
        onEvent = {}
    )
}