package com.nextgen.movieapp.utils

import com.nextgen.movieapp.data.source.local.entity.MovieEntity
import com.nextgen.movieapp.data.source.remote.response.DetailMovieResponse
import com.nextgen.movieapp.data.source.remote.response.GenresItem
import com.nextgen.movieapp.data.source.remote.response.MovieResponse
import com.nextgen.movieapp.data.source.remote.response.ResultsItem
import com.nextgen.movieapp.domain.model.DetailMovieModel
import com.nextgen.movieapp.domain.model.GenreItem
import com.nextgen.movieapp.domain.model.MovieModel

object DataMapper {

    fun resultItemToMovieModel(input: List<ResultsItem>): List<MovieModel>{
        val newList = ArrayList<MovieModel>()
        input.map { data ->
            val movie = MovieModel(
                overview = data.overview,
                originalLanguage = data.originalLanguage,
                originalTitle = data.originalTitle,
                video = data.video,
                title = data.title,
                posterPath = data.posterPath.toString(),
                backdropPath = data.backdropPath.toString(),
                releaseDate = data.releaseDate.toString(),
                popularity = data.popularity,
                voteAverage = data.voteAverage,
                id = data.id,
                adult = data.adult,
                voteCount = data.voteCount,
            )
            newList.add(movie)
        }
        return newList
    }

    fun genresItemToGenreModel(data: List<GenresItem>): List<GenreItem>{
        val newGenreItem = ArrayList<GenreItem>()
        data.map {
            val genre = GenreItem(
                name = it.name,
                id = it.id
            )
            newGenreItem.add(genre)
        }
        return newGenreItem
    }

    fun detailMovieResponseToMovieModel(input: DetailMovieResponse): DetailMovieModel{
        return DetailMovieModel(
            originalLanguage = input.originalLanguage,
            video = input.video,
            title = input.title,
            backdropPath = input.backdropPath,
            revenue = input.revenue,
            genres = DataMapper.genresItemToGenreModel(input.genres),
            popularity = input.popularity,
            id = input.id,
            voteCount = input.voteCount,
            budget = input.budget,
            overview = input.overview,
            originalTitle = input.originalTitle,
            runtime = input.runtime,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage,
            belongsToCollection = input.belongsToCollection,
            tagline = input.tagline,
            adult = input.adult,
            homepage = input.homepage,
            status = input.status
        )
    }

    fun movieEntityToDetailMovieModel(input: List<MovieEntity>): List<DetailMovieModel>{
        val newList = ArrayList<DetailMovieModel>()
        input.map { data ->
            val movie = DetailMovieModel(
                overview = data.overview,
                originalLanguage = data.originalLanguage,
                originalTitle = data.originalTitle,
                video = data.video,
                title = data.title,
                posterPath = data.posterPath,
                backdropPath = data.backdropPath,
                releaseDate = data.releaseDate,
                popularity = data.popularity,
                voteAverage = data.voteAverage,
                id = data.id,
                adult = data.adult,
                voteCount = data.voteCount,
            )
            newList.add(movie)
        }
        return newList
    }

    fun detailMovieModelToMovieEntity(data: DetailMovieModel): MovieEntity{
       return MovieEntity(
            overview = data.overview,
            originalLanguage = data.originalLanguage,
            originalTitle = data.originalTitle,
            video = data.video,
            title = data.title,
            posterPath = data.posterPath.toString(),
            backdropPath = data.backdropPath,
            releaseDate = data.releaseDate,
            popularity = data.popularity.toString(),
            voteAverage = data.voteAverage.toString(),
            id = data.id,
            adult = data.adult,
            voteCount = data.voteCount,
       )
    }

}