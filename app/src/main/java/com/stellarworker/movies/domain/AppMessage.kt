package com.stellarworker.movies.domain

sealed class AppMessage {
    data class Top250Movies(val movies: List<Top250MovieInt>) : AppMessage()
    data class InfoSnackBar(val text: String) : AppMessage()
    data class InfoToast(val text: String, val length: Int) : AppMessage()
}
