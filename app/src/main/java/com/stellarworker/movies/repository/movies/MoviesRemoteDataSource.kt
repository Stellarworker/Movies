package com.stellarworker.movies.repository.movies

import com.google.gson.GsonBuilder
import com.stellarworker.movies.domain.Top250MoviesDTO
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesRemoteDataSource {
    private val moviesApi = Retrofit.Builder()
        .baseUrl("https://imdb-api.com/API/Top250Movies/k_d3whfq1a/")
        .addConverterFactory(
            GsonConverterFactory.create(GsonBuilder().setLenient().create())
        )
        .build().create(MoviesAPI::class.java)

    fun getMovies(callback: Callback<Top250MoviesDTO>) {
        moviesApi.getMovies().enqueue(callback)
    }
}