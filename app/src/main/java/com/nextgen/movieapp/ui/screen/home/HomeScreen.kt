package com.nextgen.movieapp.ui.screen.home

import android.util.Log
import androidx.compose.foundation.layout.*
import com.nextgen.movieapp.R
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.nextgen.movieapp.MovieAppPreview
import com.nextgen.movieapp.domain.model.MovieModel
import com.nextgen.movieapp.ui.common.UiState
import com.nextgen.movieapp.ui.component.MovieItem
import com.nextgen.movieapp.ui.theme.MovieAppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
  Box(
       modifier = modifier.fillMaxSize(),
   ) {
      viewModel.uiState.collectAsState(UiState.Loading).value.let {
          when(it){
              is UiState.Loading -> {
                  viewModel.getPopularMovie()
              }
              is UiState.Success -> {
                  HomeContent(itemMovie = it.data)
              }
              is UiState.Error -> {

              }
          }

          }
      }
   }


@Composable
fun HomeContent(
    itemMovie: List<MovieModel>,
    modifier: Modifier = Modifier,
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
        )
    }
}

}

