package com.stellarworker.movies.repository.movies

import com.stellarworker.movies.domain.Top250MoviesDTO

class MoviesRepositoryImpl(private val moviesRemoteDataSource: MoviesRemoteDataSource) :
    MoviesRepository {
    override fun getMoviesFromServer(callback: retrofit2.Callback<Top250MoviesDTO>) {
        moviesRemoteDataSource.getMovies(callback)
    }
}