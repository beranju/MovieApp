package com.nextgen.movieapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nextgen.movieapp.R
import com.nextgen.movieapp.ui.theme.Alice200
import com.nextgen.movieapp.ui.theme.MovieAppTheme

@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp))
            .background(color = Alice200),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_error_64),
            contentDescription = "loading_status",
            modifier = Modifier.size(140.dp)
        )
        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
    }


}

@Preview(showBackground = true)
@Composable
fun Default() {
    MovieAppTheme {
        LoadingView()
    }

}