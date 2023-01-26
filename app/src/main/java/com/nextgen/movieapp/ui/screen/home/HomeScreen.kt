package com.nextgen.movieapp.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nextgen.movieapp.R
import com.nextgen.movieapp.data.source.remote.response.ResultsItem
import com.nextgen.movieapp.domain.model.MovieModel
import com.nextgen.movieapp.ui.common.UiState
import com.nextgen.movieapp.ui.component.MovieItem
import com.nextgen.movieapp.ui.theme.MovieAppTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onCLickItem: (Int) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column (
            modifier = Modifier.fillMaxSize()
                ){

            Text(text = stringResource(id = R.string.app_name))
            state.let {
                when(it){
                    is UiState.Loading -> {
                            viewModel.getPopularMovie()
                    }
                    is UiState.Success -> {
                        HomeContent(itemMovie = it.data, onClick = onCLickItem)
                        Text(text = "it.data")
                    }
                    is UiState.Error -> {

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
        item{
            Text(text = stringResource(id = R.string.popular_text))
        }
        items(items = itemMovie){data->
            MovieItem(
                image = data.posterPath,
                title = data.title,
                modifier = Modifier.clickable {
                    onClick(data.id)
                }
            )
        }
    }
}

