package com.nextgen.movieapp.domain.usecase

import com.nextgen.movieapp.domain.common.BaseResult
import com.nextgen.movieapp.domain.model.DetailMovieModel
import com.nextgen.movieapp.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getPopularMovie(): Flow<BaseResult<List<MovieModel>>>

    fun getDetailMovieById(id: Int): Flow<BaseResult<DetailMovieModel>>

    fun getSearchMovie(query: String): Flow<BaseResult<List<MovieModel>>>

    fun getFavoriteMovie(): Flow<BaseResult<List<DetailMovieModel>>>

    fun isFavoriteMovie(movieId: Int): Flow<BaseResult<Boolean>>

    suspend fun insertFavoriteMovie(movieModel: DetailMovieModel)

    suspend fun deleteMovie(movieModel: DetailMovieModel)
}