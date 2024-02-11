package com.example.tinkofffintechlab.domain.use_case

import android.util.Log
import com.example.tinkofffintechlab.common.Resource
import com.example.tinkofffintechlab.domain.model.Film
import com.example.tinkofffintechlab.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFilmsUseCase @Inject constructor(
    private val filmRepository: FilmRepository
) {
    operator fun invoke(): Flow<Resource<List<Film>>> = flow {
        try {
            emit(Resource.Loading())
            val films = filmRepository.fetchFilms()
            emit(Resource.Success(data = films))
        }
        catch (e: Exception) {
            emit(Resource.Error(message = "Error occurred!"))
            Log.e(javaClass.name, "Error!", e)
        }
    }.combine(filmRepository.filmFlow) { fetch, saved ->
        if (saved.isNotEmpty()) {
            Resource.Success(saved)
        } else {
            fetch
        }
    }
}