package com.example.tinkofffintechlab.domain.use_case

import com.example.tinkofffintechlab.data.repository.FakeFilmRepository
import com.example.tinkofffintechlab.data.repository.testFilm
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class GetAllFoundFilmsUseCaseTest {
    private lateinit var underTest: GetAllFoundFilmsUseCase
    private lateinit var repository: FakeFilmRepository

    @Before
    fun setUp() {
        repository = FakeFilmRepository(testFilm(isFavorite = true))
        underTest = GetAllFoundFilmsUseCase(repository)
    }

    @Test
    fun `can delete from favorites`() = runBlocking {
        val flow = underTest()
        assertEquals(repository.filmFlow, flow)
    }
}