package com.example.tinkofffintechlab.domain.use_case

import com.example.tinkofffintechlab.domain.model.Film
import com.example.tinkofffintechlab.domain.model.FilmDetails
import com.example.tinkofffintechlab.domain.repository.FilmRepository
import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(
    private val filmRepository: FilmRepository
) {
    suspend operator fun invoke(film: Film) {
        val filmDetails = filmRepository.getFilmDetailsById(film.id)
        filmRepository.addFilmToFavorites(film, filmDetails)
    }
}