package com.nextgen.movieapp.data.source.remote.retrofit

import com.nextgen.movieapp.BuildConfig
import com.nextgen.movieapp.data.source.remote.response.DetailMovieResponse
import com.nextgen.movieapp.data.source.remote.response.MovieResponse
import com.nextgen.movieapp.data.source.remote.response.SearchMovieResponse
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

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): Response<DetailMovieResponse>

    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): Response<SearchMovieResponse>

}