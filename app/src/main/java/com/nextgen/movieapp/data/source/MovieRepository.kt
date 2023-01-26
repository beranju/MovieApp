package com.nextgen.movieapp.data.source

import android.util.Log
import com.nextgen.movieapp.data.source.remote.response.DetailMovieResponse
import com.nextgen.movieapp.data.source.remote.response.ResultsItem
import com.nextgen.movieapp.data.source.remote.retrofit.ApiService
import com.nextgen.movieapp.domain.common.BaseResult
import com.nextgen.movieapp.domain.model.MovieModel
import com.nextgen.movieapp.domain.repository.IMovieRepository
import com.nextgen.movieapp.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val apiService: ApiService
    ) : IMovieRepository {

    override fun getPopularMovie(): Flow<BaseResult<List<ResultsItem>>> {
        return flow {
            try {
                val response = apiService.getPopularMovie()
                if (response.isSuccessful){
                    val data = response.body()
                    emit(BaseResult.Success(data!!.results))

                }
            }catch (e: Exception){
                emit(BaseResult.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getDetailMovieById(id: Int): Flow<BaseResult<DetailMovieResponse>> {
        return flow{
            try {
                val response = apiService.getDetailMovie(id)
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        emit(BaseResult.Success(data))
                    }
                }else{
                    emit(BaseResult.Error(response.errorBody().toString()))
                }
            }catch (e: Exception){
                emit(BaseResult.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}