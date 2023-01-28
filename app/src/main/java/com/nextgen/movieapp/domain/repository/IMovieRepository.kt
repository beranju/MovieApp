package com.nextgen.movieapp.domain.repository

import com.nextgen.movieapp.data.source.remote.response.DetailMovieResponse
import com.nextgen.movieapp.data.source.remote.response.ResultsItem
import com.nextgen.movieapp.domain.common.BaseResult
import com.nextgen.movieapp.domain.model.DetailMovieModel
import com.nextgen.movieapp.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getPopularMovie(): Flow<BaseResult<List<MovieModel>>>

    fun getDetailMovieById(id: Int): Flow<BaseResult<DetailMovieModel>>

    fun getSearchMovie(query: String): Flow<BaseResult<List<MovieModel>>>

    fun getFavoritedNews(): Flow<BaseResult<List<DetailMovieModel>>>

    fun isFavoriteMovie(movieId: Int): Flow<BaseResult<Boolean>>

    suspend fun insertFavoriteMovie(movie: DetailMovieModel)
}