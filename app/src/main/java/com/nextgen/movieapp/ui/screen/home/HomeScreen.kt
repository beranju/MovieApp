package com.nextgen.movieapp.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nextgen.movieapp.BuildConfig
import com.nextgen.movieapp.R
import com.nextgen.movieapp.data.source.remote.response.ResultsItem
import com.nextgen.movieapp.domain.model.MovieModel
import com.nextgen.movieapp.ui.common.UiState
import com.nextgen.movieapp.ui.component.*
import com.nextgen.movieapp.ui.theme.MovieAppTheme
import retrofit2.http.Query

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController,
    onCLickItem: (Int) -> Unit
) {
    val searchQuery = viewModel.searchText.collectAsState(initial = "")

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderSection(
                querySearch = searchQuery.value ,
                onClearClick = { viewModel.onClearClick() },
                onSearchTextChanged = { newQuery ->
                    viewModel.onChangedSearchQuery(newQuery)
                },
                navController = navController,
            )
            viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {
                when (it) {
                    is UiState.Loading -> {
                        viewModel.getPopularMovie()
                        LoadingView()
                    }
                    is UiState.Success -> {
                        HomeContent(
                            querySearch = searchQuery.value,
                            itemMovie = it.data,
                            onClick = onCLickItem,
                            matchFound = it.data.isNotEmpty(),
                        )
                    }
                    is UiState.Error -> {
                        ErrorView(message = it.message, action = {})
                    }
                }
            }
        }
    }
}


@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    itemMovie: List<MovieModel>,
    querySearch: String,
    matchFound: Boolean,
    onClick: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            if (matchFound){
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(160.dp),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = modifier
                ){
                    items(items = itemMovie){data->
                        MovieItem(
                            image = BuildConfig.IMAGE_BASE_URL + data.posterPath,
                            title = data.title,
                            modifier = Modifier.clickable {
                                onClick(data.id)
                            }
                        )
                    }
                }
            }else{
                if (querySearch.isNotEmpty()){
                    NothingFound()
                }

            }


    }
}



