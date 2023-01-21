package com.stellarworker.movies.repository.movies

import com.stellarworker.movies.domain.Top250MoviesDTO
import retrofit2.Call
import retrofit2.http.GET

interface MoviesAPI {
    @GET("https://imdb-api.com/API/Top250Movies/k_d3whfq1a")
    fun getMovies(): Call<Top250MoviesDTO>
}