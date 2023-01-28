package com.nextgen.movieapp.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nextgen.movieapp.data.source.remote.response.GenresItem

@Entity(tableName = "movie")
data class MovieEntity(
    val overview: String,
    val originalLanguage: String,
    val originalTitle: String,
    val video: Boolean,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val popularity: String,
    val voteAverage: String,
    @PrimaryKey
    @NonNull
    val id: Int,
    val adult: Boolean,
    val voteCount: Int
)
