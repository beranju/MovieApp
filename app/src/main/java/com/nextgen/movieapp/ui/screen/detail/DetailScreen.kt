package com.nextgen.movieapp.ui.screen.detail

import androidx.compose.foundation.layout.*
import com.nextgen.movieapp.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nextgen.movieapp.BuildConfig
import com.nextgen.movieapp.data.source.remote.response.DetailMovieResponse
import com.nextgen.movieapp.data.source.remote.response.ResultsItem
import com.nextgen.movieapp.ui.common.UiState
import com.nextgen.movieapp.ui.component.DetailMainSection
import com.nextgen.movieapp.ui.component.RateSection

@Composable
fun DetailScreen(
    movieId: Int,
    navigateBack: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        viewModel.state.collectAsState(initial = UiState.Loading).value.let { uiState->
            when(uiState){
                is UiState.Loading -> {
                    viewModel.getDetailMovieById(movieId)
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is UiState.Success -> {
                    DetailContent(data = uiState.data, navigateBack = navigateBack)
                }
                is UiState.Error -> {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        backgroundColor = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.padding(16.dp).size(300.dp).align(Alignment.Center)
                    ) {
                        Text(
                            text = uiState.message,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DetailContent(
    data: DetailMovieResponse,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        DetailMainSection(
            posterPath = "https://image.tmdb.org/t/p/original/${data.posterPath}",
            title = data.title,
            release = data.releaseDate,
            language = data.originalLanguage,
            age = data.adult,
            navigateBack = navigateBack,
        )
        Divider()
        RateSection(
            voteCount = data.voteCount,
            voteAverage = data.voteAverage,
            popularity = data.popularity)
        Divider()
        Text(text = data.overview)

    }

}
