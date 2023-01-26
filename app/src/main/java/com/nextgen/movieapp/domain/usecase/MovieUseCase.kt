package com.nextgen.movieapp.domain.usecase

import com.nextgen.movieapp.data.source.remote.response.ResultsItem
import com.nextgen.movieapp.domain.common.BaseResult
import com.nextgen.movieapp.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getPopularMovie(): Flow<BaseResult<List<ResultsItem>>>
}