package com.nextgen.movieapp.ui.screen.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nextgen.movieapp.BuildConfig
import com.nextgen.movieapp.R
import com.nextgen.movieapp.domain.model.DetailMovieModel
import com.nextgen.movieapp.ui.common.UiState
import com.nextgen.movieapp.ui.component.RateSection
import com.nextgen.movieapp.ui.theme.Alice200
import com.nextgen.movieapp.ui.theme.Blue200
import com.nextgen.movieapp.ui.theme.Shapes
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    movieId: Int,
    navigateBack: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val isFavorite = viewModel.isFavorite.collectAsState(initial = false).value
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        viewModel.state.collectAsState(initial = UiState.Loading).value.let { uiState->
            when(uiState){
                is UiState.Loading -> {
                    viewModel.getDetailMovieById(movieId)
                    viewModel.isFavoriteMovie(movieId)
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is UiState.Success -> {
                    DetailContent(
                        data = uiState.data,
                        navigateBack = navigateBack,
                        onFavoriteClicked = {
                            viewModel.insertFavoriteMovie(uiState.data)
                            viewModel.isFavoriteMovie(movieId)
                        },
                        isFavorite = isFavorite
                    )
                }
                is UiState.Error -> {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        backgroundColor = MaterialTheme.colors.onPrimary,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(300.dp)
                            .align(Alignment.Center)
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailContent(
    data: DetailMovieModel,
    navigateBack: () -> Unit,
    onFavoriteClicked: () -> Unit,
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(
        sheetContent = {
            SheetContent(data = data)
        },
        scaffoldState = scaffoldState,
        sheetPeekHeight = 200.dp,
        drawerShape = Shapes.medium,
        drawerElevation = 16.dp,
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                scope.launch {
                    if (scaffoldState.bottomSheetState.isExpanded){
                        scaffoldState.bottomSheetState.collapse()
                    }else{
                        scaffoldState.bottomSheetState.expand()
                    }
                }
            },
               modifier = Modifier.displayCutoutPadding()
            ) {
                Icon(
                    painter = painterResource(id = if (scaffoldState.bottomSheetState.isCollapsed) R.drawable.ic_keyboard_arrow_up_24 else R.drawable.ic_keyboard_down_24),
                    contentDescription = null,
                )
            }
        }
        ) { innerPadding ->
        DetailFixedContent(
            data = data,
            padding = innerPadding,
            navigateBack = navigateBack,
            onFavoriteClicked = onFavoriteClicked,
            isFavorite = isFavorite
        )
    }
}

@Composable
fun SheetContent(
    modifier: Modifier = Modifier,
    data: DetailMovieModel,

) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            RateSection(
                voteCount = data.voteCount,
                voteAverage = data.voteAverage,
                popularity = data.popularity
            )
            Text(
                text = data.title,
                style = MaterialTheme.typography.h4,
            )
        }
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyRow(
            modifier = Modifier.align(Alignment.Start)
        ){
            items(data.genres!!){genre->
                Text(
                    text = genre.name,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colors.primary)
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = data.overview)
    }

}

@Composable
fun DetailFixedContent(
    data: DetailMovieModel,
    padding: PaddingValues,
    navigateBack: () -> Unit,
    onFavoriteClicked: () -> Unit,
    isFavorite: Boolean,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.padding(paddingValues = padding)) {
        AsyncImage(
            model = BuildConfig.IMAGE_BASE_URL+data.posterPath,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(60.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                    navigateBack()
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Alice200)

            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                )
            }
            IconButton(
                onClick = { onFavoriteClicked() },
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Alice200)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier
                )
            }
        }
    }
}
