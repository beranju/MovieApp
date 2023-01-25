package com.nextgen.movieapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nextgen.movieapp.ui.navigation.Screen
import com.nextgen.movieapp.ui.screen.home.HomeScreen
import com.nextgen.movieapp.ui.theme.MovieAppTheme

@Composable
fun MovieApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = modifier
    ) {innerPadding->
        NavHost(
            navController = navController,
            startDestination = Screen.HOME.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.HOME.route){
                HomeScreen()
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

