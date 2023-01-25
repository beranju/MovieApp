package com.nextgen.movieapp.di

import com.nextgen.movieapp.ui.screen.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get()) }

    //use dsl
//    viewModelOf(::HomeViewModel)
}