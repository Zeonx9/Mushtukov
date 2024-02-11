package com.example.tinkofffintechlab.presentation.film_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tinkofffintechlab.common.Constants
import com.example.tinkofffintechlab.common.Resource
import com.example.tinkofffintechlab.domain.use_case.GetFilmDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FilmDetailsViewModel @Inject constructor(
    private val filmDetailsUseCase: GetFilmDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val filmId: Int = savedStateHandle[Constants.FILM_ID_KEY]!!
    private val _state = mutableStateOf(FilmDetailsState())
    val state = _state


    init {
        fetchDetails(filmId)
    }

    private fun fetchDetails(id: Int) {
        filmDetailsUseCase(id).onEach { result ->
            when(result) {
                is Resource.Error -> {
                    _state.value = FilmDetailsState(error = result.message!!)
                }
                is Resource.Loading -> {
                    _state.value = FilmDetailsState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = FilmDetailsState(filmDetails = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: FilmDetailsEvent) {
        when(event) {
            FilmDetailsEvent.ReloadEvent -> {
                fetchDetails(filmId)
            }

            is FilmDetailsEvent.UpEvent -> {
                event.navController.navigateUp()
            }
        }
    }
}