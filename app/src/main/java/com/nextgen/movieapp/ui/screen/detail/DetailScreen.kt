package com.nextgen.movieapp.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import com.nextgen.movieapp.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.nextgen.movieapp.BuildConfig
import com.nextgen.movieapp.data.source.remote.response.DetailMovieResponse
import com.nextgen.movieapp.data.source.remote.response.ResultsItem
import com.nextgen.movieapp.ui.common.UiState
import com.nextgen.movieapp.ui.component.DetailMainSection
import com.nextgen.movieapp.ui.component.RateSection
import com.nextgen.movieapp.ui.theme.Shapes
import kotlinx.coroutines.launch

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
    data: DetailMovieResponse,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    Box(
        modifier = modifier.fillMaxSize()
    ) {

        BottomSheetScaffold(
            sheetContent = {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp, 
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.Center)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    RateSection(
                        voteCount = data.voteCount,
                        voteAverage = data.voteAverage,
                        popularity = data.popularity,
                    )
                }
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(64.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Divider()
                    Text(text = data.overview)
                    Spacer(Modifier.height(20.dp))
                    Button(
                        onClick = {
                            scope.launch { scaffoldState.bottomSheetState.collapse() }
                        }
                    ) {
                        Text("Click to collapse sheet")
                    }

                }
            },
            scaffoldState = scaffoldState,
            sheetPeekHeight = 200.dp,
            ) {
            Box(modifier = Modifier.padding(it)) {
                AsyncImage(
                    model = BuildConfig.IMAGE_BASE_URL+data.posterPath,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { navigateBack() }
                        .background(Color.LightGray, CircleShape)
                )
            }
        }

    }


}
