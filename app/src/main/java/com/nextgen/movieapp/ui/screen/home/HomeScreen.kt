package com.nextgen.movieapp.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nextgen.movieapp.data.source.remote.response.ResultsItem
import com.nextgen.movieapp.ui.common.UiState
import com.nextgen.movieapp.ui.component.MovieItem
import com.nextgen.movieapp.ui.theme.MovieAppTheme
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel =  koinViewModel(),
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState->
            when(uiState){
                is UiState.Loading -> viewModel.getPopularMovie()
                is UiState.Success -> {
                    Text(text = "Populer Movie")
                    HomeContent(
                        itemMovie = uiState.data,
                    )
                }
                is UiState.Error -> {

                }
            }
        }


    }
}


@Composable
fun HomeContent(
    itemMovie: List<ResultsItem>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ){
        item {
            Text(text = "Popular Movie")
            Divider()
        }
        items(items = itemMovie){data->
            MovieItem(
                image = data.posterPath,
                title = data.title,
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MovieAppTheme {
        HomeScreen()
    }

}
