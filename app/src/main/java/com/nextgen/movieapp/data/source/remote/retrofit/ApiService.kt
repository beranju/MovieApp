package com.nextgen.movieapp.data.source.remote.retrofit

import com.nextgen.movieapp.BuildConfig
import com.nextgen.movieapp.data.source.remote.response.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovie (
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ):Response<MovieResponse>

}