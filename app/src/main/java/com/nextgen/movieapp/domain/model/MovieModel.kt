package com.nextgen.movieapp.domain.model

import com.google.gson.annotations.SerializedName

data class MovieModel(
    val overview: String,
    val originalLanguage: String,
    val originalTitle: String,
    val video: Boolean,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val popularity: Any,
    val voteAverage: Any,
    val id: Int,
    val adult: Boolean,
    val voteCount: Int
)
