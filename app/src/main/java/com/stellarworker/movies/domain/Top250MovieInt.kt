package com.stellarworker.movies.domain

import android.os.Parcelable
import com.stellarworker.movies.utils.EMPTY
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Top250MovieInt(
    val id: String = String.EMPTY,
    val rank: String = String.EMPTY,
    val title: String = String.EMPTY,
    val fullTitle: String = String.EMPTY,
    val year: String = String.EMPTY,
    val image: String = String.EMPTY,
    val crew: String = String.EMPTY,
    val imDbRating: String = String.EMPTY,
    val imDbRatingCount: String = String.EMPTY,
) : Parcelable