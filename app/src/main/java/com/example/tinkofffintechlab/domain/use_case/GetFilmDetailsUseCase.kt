package com.example.tinkofffintechlab.domain.use_case

import android.util.Log
import com.example.tinkofffintechlab.common.Resource
import com.example.tinkofffintechlab.domain.model.FilmDetails
import com.example.tinkofffintechlab.domain.repository.FilmRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFilmDetailsUseCase @Inject constructor(
    private val filmRepository: FilmRepository
) {

    operator fun invoke(filmId: Int): Flow<Resource<FilmDetails>> = flow {
        try {
            emit(Resource.Loading())
            val filmDetails = filmRepository.getFilmDetailsById(filmId)
            emit(Resource.Success(filmDetails))
        }
        catch (e: Exception) {
            emit(Resource.Error("Error occurred!"))
            Log.e(javaClass.name, "error during getFilmDetailsById id=$filmId!", e)
        }
    }
}