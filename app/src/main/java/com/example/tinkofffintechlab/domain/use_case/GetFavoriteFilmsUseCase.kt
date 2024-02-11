package com.example.tinkofffintechlab.domain.use_case

import com.example.tinkofffintechlab.domain.model.Film
import com.example.tinkofffintechlab.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteFilmsUseCase @Inject constructor(
    private val filmRepository: FilmRepository
) {
    operator fun invoke(): Flow<List<Film>> {
        return filmRepository.getFavoriteFilms()
    }
}