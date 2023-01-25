package com.nextgen.movieapp.data.source

import com.nextgen.movieapp.data.source.remote.response.ResultsItem
import com.nextgen.movieapp.data.source.remote.retrofit.ApiService
import com.nextgen.movieapp.domain.common.BaseResult
import com.nextgen.movieapp.domain.model.MovieModel
import com.nextgen.movieapp.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository(private val apiService: ApiService) : IMovieRepository {

    override fun getPopularNews(): Flow<BaseResult<List<MovieModel>>> {
        return flow {
            try {
                val response = apiService.getPopularMovie()
                if (response.isSuccessful){
                    val data = response.body()
                    if (data != null){
                        data.results.forEach { data->


                        }
                        BaseResult.Success(data.results)
                    }
                }
            }catch (e: Exception){
                BaseResult.Error(e.localizedMessage?.toString() ?: e.message.toString())
            }

        }
    }

}