package com.nextgen.movieapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nextgen.movieapp.ui.navigation.Screen
import com.nextgen.movieapp.ui.screen.about.AboutScreen
import com.nextgen.movieapp.ui.screen.detail.DetailScreen
import com.nextgen.movieapp.ui.screen.favorite.FavoriteScreen
import com.nextgen.movieapp.ui.screen.home.HomeScreen
import com.nextgen.movieapp.ui.theme.MovieAppTheme


@Composable
fun MovieApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    Scaffold(
        modifier = modifier,
    ) {innerPadding->
        NavHost(
            navController = navController,
            startDestination = Screen.HOME.route,
            modifier = modifier.padding(innerPadding)
        ){
            composable(route = Screen.HOME.route){
                HomeScreen(
                    navController = navController,
                    onCLickItem = {id->
                        navController.navigate(Screen.DETAILMOVIE.createRoute(id))
                    }
                )
            }
            composable(route = Screen.ABOUT.route){
                AboutScreen(
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable(route = Screen.FAVORITE.route){
                FavoriteScreen(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    onClickItem = {id ->
                        navController.navigate(Screen.DETAILMOVIE.createRoute(id))
                    }
                )
            }
            composable(
                route = Screen.DETAILMOVIE.route,
                arguments = listOf(
                    navArgument("movieId"){
                        type = NavType.IntType
                    }
                )
            ){
                val id =it.arguments?.getInt("movieId") ?: 1
                DetailScreen(
                    movieId = id,
                    navController = navController
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MovieAppPreview() {
    MovieAppTheme {
        MovieApp()
    }
}

