package com.nextgen.movieapp.ui.screen.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nextgen.movieapp.data.source.remote.response.ResultsItem

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = "FavoriteScreen")
    }
}

@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier,
    listMovie: List<ResultsItem>,
    onClickItem: () -> Unit,
) {
    LazyColumn(
        modifier = modifier
    ){
        items(listMovie){data->

        }

    }

}