package com.nextgen.movieapp.ui.screen.favorite

import android.support.v4.os.IResultReceiver.Default
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nextgen.movieapp.BuildConfig
import com.nextgen.movieapp.data.source.remote.response.ResultsItem
import com.nextgen.movieapp.domain.model.DetailMovieModel
import com.nextgen.movieapp.ui.common.UiState
import com.nextgen.movieapp.ui.component.ActionBarTemplate
import com.nextgen.movieapp.ui.component.ErrorView
import com.nextgen.movieapp.ui.component.LoadingView
import com.nextgen.movieapp.ui.component.NothingFound
import com.nextgen.movieapp.ui.theme.Alice200
import com.nextgen.movieapp.ui.theme.MovieAppTheme
import com.nextgen.movieapp.ui.theme.Shapes

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        ActionBarTemplate(
            navigateBack = { navigateBack() },
            title = "Your Favorite Movie"
        )
        viewModel.state.collectAsState(initial = UiState.Loading).value.let { result ->
            when(result){
                is UiState.Loading -> {
                    viewModel.getFavoriteMovie()
                    LoadingView()
                }
                is UiState.Success -> {
                    FavoriteContent(
                        listMovie =result.data ,
                        onClickItem = {},
                    )
                }
                is UiState.Error -> {
                    ErrorView(message = result.message, action = {})
                }
            }
        }
    }
}

@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier,
    listMovie: List<DetailMovieModel>,
    onClickItem: () -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ){
        items(listMovie){data->
            FavoriteItem(
                title = data.title,
                overview = data.overview,
                posterPath = data.posterPath.toString()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

    }

}

@Composable
fun FavoriteItem(
    modifier: Modifier = Modifier,
    title: String,
    overview: String,
    posterPath: String,

) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(Shapes.small)
            .background(Alice200)
    ) {
        AsyncImage(
            model = BuildConfig.IMAGE_BASE_URL + posterPath,
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(Shapes.medium)
                .weight(1f)
        )
        Column(
            modifier = Modifier
                .weight(2f)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h5,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
            Text(
                text = overview,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun Default() {
    MovieAppTheme {
        FavoriteItem(title = "dfkljgjdfk", overview = "lkfgjajfgfdg", posterPath = "")
    }

}