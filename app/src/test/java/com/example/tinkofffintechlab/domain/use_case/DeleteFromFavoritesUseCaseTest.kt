package com.example.tinkofffintechlab.domain.use_case

import com.example.tinkofffintechlab.data.repository.FakeFilmRepository
import com.example.tinkofffintechlab.data.repository.testFilm
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class DeleteFromFavoritesUseCaseTest {

    private lateinit var underTest: DeleteFromFavoritesUseCase
    private lateinit var repository: FakeFilmRepository

    @Before
    fun setUp() {
        repository = FakeFilmRepository(testFilm(isFavorite = true))
        underTest = DeleteFromFavoritesUseCase(repository)
    }

    @Test
    fun `can delete from favorites`() = runBlocking {
        underTest(repository.filmFlow.first()[0])
        assertEquals(false, repository.filmFlow.first()[0].isFavorite, )
    }
}