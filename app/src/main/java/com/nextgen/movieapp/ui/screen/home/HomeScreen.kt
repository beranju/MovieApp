package com.nextgen.movieapp.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nextgen.movieapp.BuildConfig
import com.nextgen.movieapp.domain.model.MovieModel
import com.nextgen.movieapp.ui.common.UiState
import com.nextgen.movieapp.ui.component.*

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



