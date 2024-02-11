package com.example.tinkofffintechlab.presentation.favorites_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tinkofffintechlab.common.Resource
import com.example.tinkofffintechlab.domain.use_case.DeleteFromFavoritesUseCase
import com.example.tinkofffintechlab.domain.use_case.GetFavoriteFilmsUseCase
import com.example.tinkofffintechlab.domain.use_case.GetFilmDetailsUseCase
import com.example.tinkofffintechlab.presentation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteFilmsUseCase: GetFavoriteFilmsUseCase,
    private val deleteFromFavoritesUseCase: DeleteFromFavoritesUseCase,
    private val getFilmDetailsUseCase: GetFilmDetailsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(FavoritesState())
    val state = _state

    private var watchDetailsJob: Job? = null

    init {
        getFavorites()
    }

    private fun getFavorites() {
        getFavoriteFilmsUseCase().onEach {
            _state.value = FavoritesState(it)
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: FavoritesEvent) {
        when(event) {
            is FavoritesEvent.FilmClickEvent -> {
                event.navController.navigate(
                    Screen.FilmDetailsScreen.withArgs(event.film.id)
                )
            }

            is FavoritesEvent.DeleteFromFavoriteEvent -> {
                viewModelScope.launch {
                    deleteFromFavoritesUseCase(event.film)
                }
            }

            is FavoritesEvent.SearchClickEvent -> {
                event.navController.navigate(
                    Screen.SearchScreen.withArgs(true)
                )
            }

            is FavoritesEvent.FilmClickLandscapeEvent -> {
                watchDetailsJob?.cancel()
                watchDetailsJob = getFilmDetailsUseCase(event.film.id).onEach { result ->
                    when(result) {
                        is Resource.Success -> {
                            _state.value = _state.value.copy(
                                filmDetails = result.data
                            )
                        }
                        else -> Unit
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}