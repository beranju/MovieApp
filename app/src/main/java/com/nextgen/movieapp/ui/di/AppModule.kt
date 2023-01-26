package com.nextgen.movieapp.ui.di

import com.nextgen.movieapp.domain.usecase.MovieUseCase
import com.nextgen.movieapp.domain.usecase.UseCaseInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    abstract fun provideUseCase(useCaseInteractor: UseCaseInteractor): MovieUseCase
}