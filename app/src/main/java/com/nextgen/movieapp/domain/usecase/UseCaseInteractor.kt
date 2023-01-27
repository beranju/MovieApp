package com.nextgen.movieapp.domain.usecase

import com.nextgen.movieapp.data.source.remote.response.DetailMovieResponse
import com.nextgen.movieapp.data.source.remote.response.ResultsItem
import com.nextgen.movieapp.domain.common.BaseResult
import com.nextgen.movieapp.domain.model.MovieModel
import com.nextgen.movieapp.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseInteractor @Inject constructor(private val repository: IMovieRepository): MovieUseCase {
    override fun getPopularMovie(): Flow<BaseResult<List<ResultsItem>>> =
        repository.getPopularMovie()

    override fun getDetailMovieById(id: Int): Flow<BaseResult<DetailMovieResponse>> =
        repository.getDetailMovieById(id)

    override fun getSearchMovie(query: String): Flow<BaseResult<List<ResultsItem>>> {
        return repository.getSearchMovie(query)
    }
}