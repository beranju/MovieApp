package com.nextgen.movieapp.di

import com.nextgen.movieapp.data.source.MovieRepository
import com.nextgen.movieapp.data.source.remote.retrofit.ApiService
import com.nextgen.movieapp.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providesRepository(repository: MovieRepository): IMovieRepository

}