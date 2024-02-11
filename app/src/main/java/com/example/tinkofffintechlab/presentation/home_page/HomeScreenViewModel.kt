package com.example.tinkofffintechlab.presentation.home_page

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tinkofffintechlab.common.Resource
import com.example.tinkofffintechlab.domain.use_case.AddToFavoritesUseCase
import com.example.tinkofffintechlab.domain.use_case.GetFilmDetailsUseCase
import com.example.tinkofffintechlab.domain.use_case.GetFilmsUseCase
import com.example.tinkofffintechlab.presentation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getFilmsUseCase: GetFilmsUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val getFilmDetailsUseCase: GetFilmDetailsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(HomeScreenState())
    val state = _state

    private var watchDetailsJob: Job? = null

    init {
        fetchFilms()
    }

    private fun fetchFilms() {
        getFilmsUseCase().onEach { result ->
            when(result) {
                is Resource.Loading -> {
                    _state.value = HomeScreenState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = HomeScreenState(error = result.message!!)
                }
                is Resource.Success -> {
                    _state.value = HomeScreenState(films = result.data!!)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: HomeScreenEvent) {
        when(event) {
            is HomeScreenEvent.FilmClickEvent -> {
                event.navController.navigate(
                    Screen.FilmDetailsScreen.withArgs(event.film.id)
                )
            }
            HomeScreenEvent.ReloadEvent -> {
                fetchFilms()
            }

            is HomeScreenEvent.AddToFavoritesEvent -> {
                viewModelScope.launch {
                    addToFavoritesUseCase(event.film)
                }
            }

            is HomeScreenEvent.SearchClickedEvent -> {
                event.navController.navigate(
                    Screen.SearchScreen.withArgs(false)
                )
            }

            is HomeScreenEvent.FilmClickLandscapeEvent -> {
                watchDetailsJob?.let {
                    it.cancel()
                    Log.i(javaClass.name, "job cancelled!")
                }
                watchDetailsJob = getFilmDetailsUseCase(event.film.id).onEach {result ->
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