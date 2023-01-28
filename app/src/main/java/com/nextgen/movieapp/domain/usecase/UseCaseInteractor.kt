package com.nextgen.movieapp.domain.usecase

import com.nextgen.movieapp.data.source.remote.response.DetailMovieResponse
import com.nextgen.movieapp.data.source.remote.response.ResultsItem
import com.nextgen.movieapp.domain.common.BaseResult
import com.nextgen.movieapp.domain.model.DetailMovieModel
import com.nextgen.movieapp.domain.model.MovieModel
import com.nextgen.movieapp.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseInteractor @Inject constructor(private val repository: IMovieRepository): MovieUseCase {
    override fun getPopularMovie(): Flow<BaseResult<List<MovieModel>>> =
        repository.getPopularMovie()

    override fun getDetailMovieById(id: Int): Flow<BaseResult<DetailMovieModel>> =
        repository.getDetailMovieById(id)

    override fun getSearchMovie(query: String): Flow<BaseResult<List<MovieModel>>> {
        return repository.getSearchMovie(query)
    }

    override fun getFavoriteMovie(): Flow<BaseResult<List<DetailMovieModel>>> =
        repository.getFavoritedNews()

    override fun isFavoriteMovie(movieId: Int): Flow<BaseResult<Boolean>> {
        return repository.isFavoriteMovie(movieId)
    }

    override suspend fun insertFavoriteMovie(movieModel: DetailMovieModel) =
        repository.insertFavoriteMovie(movieModel)

    override suspend fun deleteMovie(movieModel: DetailMovieModel) {
        return repository.deleteMovie(movieModel)
    }
}