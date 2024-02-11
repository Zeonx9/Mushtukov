package com.example.tinkofffintechlab.data.remote.api

import com.example.tinkofffintechlab.common.Constants
import com.example.tinkofffintechlab.data.remote.dto.FilmDetailsDto
import com.example.tinkofffintechlab.data.remote.dto.TopFilmListResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmsApi {

    @GET("api/v2.2/films/top")
    suspend fun getTopFilms(
        @Query("type") type: String = "TOP_100_POPULAR_FILMS",
        @Header("x-api-key") apiKey: String = Constants.API_KEY
    ): TopFilmListResponse

    @GET("api/v2.2/films/{id}")
    suspend fun getFilmDetails(
        @Path("id") filmId: Int,
        @Header("x-api-key") apiKey: String = Constants.API_KEY
    ): FilmDetailsDto
}