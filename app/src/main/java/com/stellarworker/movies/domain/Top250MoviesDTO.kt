package com.stellarworker.movies.domain

data class Top250MoviesDTO(
    val items: List<Top250MovieDTO>?,
    val errorMessage: String?
)

data class Top250MovieDTO(
    val id: String?,
    val rank: String?,
    val title: String?,
    val fullTitle: String?,
    val year: String?,
    val image: String?,
    val crew: String?,
    val imDbRating: String?,
    val imDbRatingCount: String?,
)

