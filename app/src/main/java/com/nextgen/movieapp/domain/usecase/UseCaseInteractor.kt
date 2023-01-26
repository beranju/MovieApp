package com.nextgen.movieapp.domain.usecase

import com.nextgen.movieapp.domain.common.BaseResult
import com.nextgen.movieapp.domain.model.MovieModel
import com.nextgen.movieapp.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseInteractor @Inject constructor(private val repository: IMovieRepository): MovieUseCase {
    override fun getPopularMovie(): Flow<BaseResult<List<MovieModel>>> =
        repository.getPopularMovie()
}