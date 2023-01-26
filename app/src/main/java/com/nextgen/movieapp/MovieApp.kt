package com.nextgen.movieapp

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nextgen.movieapp.ui.common.UiState
import com.nextgen.movieapp.ui.component.MovieItem
import com.nextgen.movieapp.ui.screen.home.HomeContent
import com.nextgen.movieapp.ui.screen.home.HomeScreen
import com.nextgen.movieapp.ui.screen.home.HomeViewModel
import com.nextgen.movieapp.ui.theme.MovieAppTheme
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getKoin
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieApp(
    modifier: Modifier = Modifier,
) {
    val viewModel = hiltViewModel<HomeViewModel>()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        HomeScreen(viewModel = viewModel)
    }
//    Scaffold(
//        modifier = modifier
//    ) {innerPadding->
//

//        NavHost(
//            navController = navController,
//            startDestination = Screen.HOME.route,
//            modifier = Modifier.padding(innerPadding)
//        ){
//            composable(Screen.HOME.route){
//                HomeScreen()
//            }
//
//            composable(Screen.ABOUT.route){
//                AboutScreen()
//            }
//
//        }

//    }

}

@Preview(showBackground = true)
@Composable
fun MovieAppPreview() {
    MovieAppTheme {
        MovieApp()
    }

}

