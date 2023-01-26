package com.nextgen.movieapp.data.source

import android.util.Log
import com.nextgen.movieapp.data.source.remote.response.ResultsItem
import com.nextgen.movieapp.data.source.remote.retrofit.ApiService
import com.nextgen.movieapp.domain.common.BaseResult
import com.nextgen.movieapp.domain.model.MovieModel
import com.nextgen.movieapp.domain.repository.IMovieRepository
import com.nextgen.movieapp.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
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
                    BaseResult.Success(data)
                }
            }catch (e: Exception){
                BaseResult.Error(e.localizedMessage!!.toString(), null)
            }
        }
    }

}