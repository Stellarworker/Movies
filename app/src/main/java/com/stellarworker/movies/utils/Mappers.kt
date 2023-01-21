package com.stellarworker.movies.utils

import com.stellarworker.movies.domain.Top250MovieDTO
import com.stellarworker.movies.domain.Top250MovieInt
import com.stellarworker.movies.domain.Top250MoviesDTO

class Mappers {

    fun map(top250MovieDTO: Top250MovieDTO) =
        Top250MovieInt(
            id = top250MovieDTO.id ?: NONE,
            rank = top250MovieDTO.rank ?: NONE,
            title = top250MovieDTO.title ?: NONE,
            fullTitle = top250MovieDTO.fullTitle ?: NONE,
            year = top250MovieDTO.year ?: NONE,
            image = top250MovieDTO.image ?: NONE,
            crew = top250MovieDTO.crew ?: NONE,
            imDbRating = top250MovieDTO.imDbRating ?: NONE,
            imDbRatingCount = top250MovieDTO.imDbRatingCount ?: NONE
        )

    fun map(top250MoviesDTO: Top250MoviesDTO): List<Top250MovieInt> =
        top250MoviesDTO.items?.map { top250MovieDTO -> map(top250MovieDTO) } ?: listOf()

    companion object {
        private const val NONE = "NONE"
    }

}