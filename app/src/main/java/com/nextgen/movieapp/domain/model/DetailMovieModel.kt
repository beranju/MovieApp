package com.nextgen.movieapp.domain.model

data class DetailMovieModel(
    val originalLanguage: String,
    val video: Boolean,
    val title: String,
    val backdropPath: String,
    val revenue: Int? = null,
    val genres: List<GenreItem>? = null,
    val popularity: Any,
    val id: Int,
    val voteCount: Int,
    val budget: Int? = null,
    val overview: String,
    val originalTitle: String,
    val runtime: Int? = null,
    val posterPath: Any,
    val releaseDate: String,
    val voteAverage: Any,
    val belongsToCollection: Any? = null,
    val tagline: String?= null,
    val adult: Boolean,
    val homepage: String? = null,
    val status: String?=null
)

data class GenreItem(
    val name: String,
    val id: Int
)
