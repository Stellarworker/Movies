package com.stellarworker.movies.repository.movies

import com.stellarworker.movies.domain.Top250MoviesDTO

interface MoviesRepository {
    fun getMoviesFromServer(callback: retrofit2.Callback<Top250MoviesDTO>)
}