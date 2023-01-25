package com.nextgen.movieapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nextgen.movieapp.ui.theme.MovieAppTheme

// https://developers.themoviedb.org/3/discover/movie-discover
@Composable
fun MovieApp(
    modifier: Modifier = Modifier
) {

}

@Preview(showBackground = true)
@Composable
fun MovieAppPreview() {
    MovieAppTheme {
        MovieApp()
    }

}

