package com.nextgen.movieapp.ui.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import com.nextgen.movieapp.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nextgen.movieapp.BuildConfig
import com.nextgen.movieapp.data.source.remote.response.ResultsItem
import com.nextgen.movieapp.ui.component.DetailMainSection
import com.nextgen.movieapp.ui.component.RateSection

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = stringResource(id = R.string.app_name))
    }


}

@Composable
fun DetailContent(
    data: ResultsItem,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        DetailMainSection(
            posterPath = "https://image.tmdb.org/t/p/original/${data.posterPath}",
            title = data.title,
            release = data.releaseDate,
            language = data.originalLanguage,
            age = data.adult
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
