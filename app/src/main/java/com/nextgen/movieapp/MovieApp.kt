package com.nextgen.movieapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nextgen.movieapp.ui.navigation.Screen
import com.nextgen.movieapp.ui.screen.about.AboutScreen
import com.nextgen.movieapp.ui.screen.home.HomeScreen
import com.nextgen.movieapp.ui.screen.home.HomeViewModel
import com.nextgen.movieapp.ui.theme.MovieAppTheme


@Composable
fun MovieApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        topBar = {
                 TopBar(navController)
        },
        modifier = modifier,
    ) {innerPadding->
        NavHost(
            navController = navController,
            startDestination = Screen.HOME.route,
            modifier = modifier.padding(innerPadding)
        ){
            composable(route = Screen.HOME.route){
                HomeScreen(
                    onCLickItem = {}
                )
            }
            composable(route = Screen.ABOUT.route){
                AboutScreen()
            }
        }
    }
}

@Composable
fun TopBar(
    navController: NavHostController
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name)
            )
        },
        actions = {
            IconButton(onClick = {
                navController.navigate(Screen.ABOUT.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(id = R.string.iconAbout)
                )
            }
        }
    )
    
}

@Preview(showBackground = true)
@Composable
fun MovieAppPreview() {
    MovieAppTheme {
        MovieApp()
    }

}

