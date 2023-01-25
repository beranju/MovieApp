package com.nextgen.movieapp.domain.repository

import com.nextgen.movieapp.domain.common.BaseResult
import com.nextgen.movieapp.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getPopularNews(): Flow<BaseResult<List<MovieModel>>>
}