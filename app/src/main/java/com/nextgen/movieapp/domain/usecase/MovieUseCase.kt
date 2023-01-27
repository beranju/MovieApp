package com.nextgen.movieapp.domain.usecase

import com.nextgen.movieapp.data.source.remote.response.DetailMovieResponse
import com.nextgen.movieapp.data.source.remote.response.ResultsItem
import com.nextgen.movieapp.domain.common.BaseResult
import com.nextgen.movieapp.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface MovieUseCase {
    fun getPopularMovie(): Flow<BaseResult<List<ResultsItem>>>

    fun getDetailMovieById(id: Int): Flow<BaseResult<DetailMovieResponse>>

    fun getSearchMovie(query: String): Flow<BaseResult<List<ResultsItem>>>
}