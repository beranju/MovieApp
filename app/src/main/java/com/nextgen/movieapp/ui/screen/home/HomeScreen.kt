package com.nextgen.movieapp.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nextgen.movieapp.data.source.remote.response.ResultsItem
import com.nextgen.movieapp.ui.common.UiState
import com.nextgen.movieapp.ui.component.MovieItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onCLickItem: (Int) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {
            when(it){
                is UiState.Loading -> {
                        viewModel.getPopularMovie()
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is UiState.Success -> {
                    HomeContent(itemMovie = it.data, onClick = onCLickItem)
                }
                is UiState.Error -> {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        backgroundColor = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text(
                            text = it.message,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun HomeContent(
    itemMovie: List<ResultsItem>,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
) {
    LazyVerticalGrid(
    columns = GridCells.Adaptive(160.dp),
    contentPadding = PaddingValues(16.dp),
    horizontalArrangement = Arrangement.spacedBy(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp),
    modifier = modifier
    ){
        items(items = itemMovie){data->
            MovieItem(
                image = "https://image.tmdb.org/t/p/original/${data.posterPath}",
                title = data.title,
                modifier = Modifier.clickable {
                    onClick(data.id)
                }
            )
        }
    }
}

