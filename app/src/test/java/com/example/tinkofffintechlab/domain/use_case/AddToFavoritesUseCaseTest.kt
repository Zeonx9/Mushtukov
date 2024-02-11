package com.example.tinkofffintechlab.domain.use_case

import com.example.tinkofffintechlab.data.repository.FakeFilmRepository
import com.example.tinkofffintechlab.data.repository.testFilm
import com.example.tinkofffintechlab.domain.model.Film
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class AddToFavoritesUseCaseTest {

    private lateinit var underTest: AddToFavoritesUseCase
    private lateinit var repository: FakeFilmRepository

    @Before
    fun setUp() {
        repository = FakeFilmRepository(testFilm())
        underTest = AddToFavoritesUseCase(repository)
    }

    @Test
    fun `can add to favorites`() = runBlocking {
        val film = repository.filmFlow.first()[0]
        underTest(film)
        assertEquals(repository.filmFlow.first()[0].isFavorite, true)
    }
}