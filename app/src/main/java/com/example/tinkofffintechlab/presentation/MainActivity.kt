package com.example.tinkofffintechlab.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tinkofffintechlab.common.Constants
import com.example.tinkofffintechlab.presentation.favorites_screen.FavoritesScreen
import com.example.tinkofffintechlab.presentation.favorites_screen.FavoritesViewModel
import com.example.tinkofffintechlab.presentation.film_details.FilmDetailsScreen
import com.example.tinkofffintechlab.presentation.film_details.FilmDetailsViewModel
import com.example.tinkofffintechlab.presentation.home_page.HomeScreen
import com.example.tinkofffintechlab.presentation.home_page.HomeScreenViewModel
import com.example.tinkofffintechlab.presentation.search_screen.SearchScreen
import com.example.tinkofffintechlab.presentation.search_screen.SearchScreenViewModel
import com.example.tinkofffintechlab.presentation.ui.theme.TinkoffFintechLabTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TinkoffFintechLabTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {
                        composable(route = Screen.HomeScreen.route) {
                            val viewModel: HomeScreenViewModel = hiltViewModel()
                            HomeScreen(
                                navController,
                                viewModel.state.value,
                                viewModel::onEvent
                            )
                        }
                        composable(
                            route = Screen.FilmDetailsScreen.route + "/{${Constants.FILM_ID_KEY}}",
                            arguments = listOf(
                                navArgument(Constants.FILM_ID_KEY) {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            val viewModel: FilmDetailsViewModel = hiltViewModel()
                            FilmDetailsScreen(
                                navController,
                                viewModel.state.value,
                                viewModel::onEvent
                            )
                        }
                        composable(route = Screen.FavoritesScreen.route) {
                            val viewModel: FavoritesViewModel = hiltViewModel()
                            FavoritesScreen(
                                navController,
                                viewModel.state.value,
                                viewModel::onEvent
                            )
                        }
                        composable(
                            route = Screen.SearchScreen.route + "/{${Constants.SEARCH_FAVORITE_KEY}}",
                            arguments = listOf(
                                navArgument(name = Constants.SEARCH_FAVORITE_KEY) {
                                    type = NavType.BoolType
                                }
                            )
                        ) {
                            val viewModel: SearchScreenViewModel = hiltViewModel()
                            SearchScreen(
                                navController,
                                viewModel.state.value,
                                viewModel::onEvent
                            )
                        }
                    }
                }
            }
        }
    }
}