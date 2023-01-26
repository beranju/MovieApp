package com.nextgen.movieapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nextgen.movieapp.R
import com.nextgen.movieapp.ui.screen.detail.DetailScreen
import com.nextgen.movieapp.ui.theme.MovieAppTheme
import com.nextgen.movieapp.ui.theme.Shapes

@Composable
fun DetailMainSection(
    posterPath: String,
    title: String,
    release: String,
    language: String,
    age: Boolean,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Box {
            AsyncImage(
                model = posterPath,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(150.dp)
                    .height(210.dp)
                    .clip(shape = Shapes.medium)
                    .shadow(elevation = 8.dp)
            )
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier.padding(8.dp).clickable { navigateBack() }
                    .background(Color.LightGray, CircleShape)
            )
        }

        Column {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            HorizontalTextPil(
                label = "Rilis",
                input = release
            )
            HorizontalTextPil(
                label = "Bahasa",
                input = language
            )
            HorizontalTextPil(
                label = "Usia",
                input = if (age) "Dewasa" else "Semua Umur"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailSectionPreview() {
    MovieAppTheme {
        DetailMainSection(
            posterPath = "",
            title = "Wellcome to the Jungle",
            release = "2022-01-25",
            language = "english",
            age = true,
            navigateBack = {}
        )
    }

}