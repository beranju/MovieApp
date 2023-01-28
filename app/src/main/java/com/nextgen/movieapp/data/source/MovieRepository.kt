package com.nextgen.movieapp.data.source

import android.util.Log
import com.nextgen.movieapp.data.source.local.room.MovieDao
import com.nextgen.movieapp.data.source.remote.response.DetailMovieResponse
import com.nextgen.movieapp.data.source.remote.response.ResultsItem
import com.nextgen.movieapp.data.source.remote.retrofit.ApiService
import com.nextgen.movieapp.domain.common.BaseResult
import com.nextgen.movieapp.domain.model.DetailMovieModel
import com.nextgen.movieapp.domain.model.MovieModel
import com.nextgen.movieapp.domain.repository.IMovieRepository
import com.nextgen.movieapp.ui.screen.detail.DetailViewModel
import com.nextgen.movieapp.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val movieDao: MovieDao
    ) : IMovieRepository {

    override fun getPopularMovie(): Flow<BaseResult<List<MovieModel>>> {
        return flow {
            try {
                val response = apiService.getPopularMovie()
                if (response.isSuccessful){
                    response.body()?.let {
                        val data = DataMapper.resultItemToMovieModel(it.results)
                        emit(BaseResult.Success(data))
                    }
                }
            }catch (e: Exception){
                emit(BaseResult.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getDetailMovieById(id: Int): Flow<BaseResult<DetailMovieModel>> {
        return flow{
            try {
                val response = apiService.getDetailMovie(id)
                if (response.isSuccessful){
                    response.body()?.let {
                        val data = DataMapper.detailMovieResponseToMovieModel(it)
                        emit(BaseResult.Success(data))
                    }
                }
            }catch (e: Exception){
                emit(BaseResult.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getSearchMovie(query: String): Flow<BaseResult<List<MovieModel>>> {
        return flow {
            try {
                val response = apiService.getSearchMovie(query)
                if (response.isSuccessful){
                    response.body()?.let {
                        val data = DataMapper.resultItemToMovieModel(it.results)
                        emit(BaseResult.Success(data))
                    }
                }
            }catch (e: Exception){
                emit(BaseResult.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getFavoritedNews(): Flow<BaseResult<List<DetailMovieModel>>>{
        return flow {
            movieDao.getAllNews()
                .collect{listMovie->
                    val data = DataMapper.movieEntityToDetailMovieModel(listMovie)
                    emit(BaseResult.Success(data))
                }
        }.flowOn(Dispatchers.IO)
    }

    override fun isFavoriteMovie(movieId: Int): Flow<BaseResult<Boolean>> {
        return flow {
            val result = movieDao.isFavoriteMovie(movieId)
            emit(BaseResult.Success(result))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun insertFavoriteMovie(movie: DetailMovieModel){
        DataMapper.detailMovieModelToMovieEntity(movie).let {
            movieDao.insertMovie(it)
        }
    }

    override suspend fun deleteMovie(movie: DetailMovieModel) {
        DataMapper.detailMovieModelToMovieEntity(movie).let {
            movieDao.delete(it)
        }
    }


}