package com.example.tinkofffintechlab.domain.use_case

import com.example.tinkofffintechlab.domain.model.Film
import com.example.tinkofffintechlab.domain.repository.FilmRepository
import javax.inject.Inject

class DeleteFromFavoritesUseCase @Inject constructor(
    private val filmRepository: FilmRepository
) {
    suspend operator fun invoke(film: Film) {
        filmRepository.deleteFromFavorites(film)
    }
}