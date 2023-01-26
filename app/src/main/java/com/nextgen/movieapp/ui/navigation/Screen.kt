package com.nextgen.movieapp.ui.navigation

sealed class Screen(val route: String) {
    object HOME: Screen("home")
    object ABOUT: Screen("about")
    object DETAILMOVIE: Screen("home/{movieId}"){
        fun createRoute(movieId: Int) = "home/$movieId"
    }
}