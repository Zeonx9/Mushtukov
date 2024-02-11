package com.example.tinkofffintechlab.presentation.search_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tinkofffintechlab.common.Constants
import com.example.tinkofffintechlab.domain.model.Film
import com.example.tinkofffintechlab.domain.use_case.GetAllFoundFilmsUseCase
import com.example.tinkofffintechlab.presentation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getAllFoundFilmsUseCase: GetAllFoundFilmsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(SearchScreenState())
    val state: State<SearchScreenState> = _state
    private var allFilms: List<Film> = emptyList()
    private val favoritesOnly: Boolean = savedStateHandle[Constants.SEARCH_FAVORITE_KEY] ?: false


    init {
        viewModelScope.launch {
            getAllFoundFilmsUseCase().collect {
                allFilms = it
            }
        }
    }

    fun onEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.IsActiveChangedEvent -> Unit
            SearchEvent.PreformSearchEvent ->  {
                _state.value = _state.value.copy(
                    results = allFilms.filter { checkFilm(it) }
                )
            }
            is SearchEvent.QueryChangedEvent -> {
                _state.value = _state.value.copy(
                    query = event.newQuery
                )
            }
            is SearchEvent.UpEvent -> {
                event.navController.navigateUp()
            }

            is SearchEvent.FilmClickEvent -> {
                event.navController.navigate(
                    Screen.FilmDetailsScreen.withArgs(event.film.id)
                )
            }
        }
    }

    private fun checkFilm(film: Film): Boolean {
        return film.name.lowercase().contains(state.value.query) &&
                (!favoritesOnly || film.isFavorite)
    }

}