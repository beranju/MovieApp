package com.nextgen.movieapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nextgen.movieapp.ui.theme.MovieAppTheme
import com.nextgen.movieapp.R
import com.nextgen.movieapp.ui.theme.Alice200

@Composable
fun RateSection(
    voteCount: Int,
    voteAverage: Any,
    popularity: Any,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = Alice200)
            .heightIn(min = 70.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.weight(2f).padding(start = 16.dp)) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "vote count",
                tint = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = "$voteAverage ( $voteCount review)"
            )
        }
        Row(modifier = Modifier.weight(1f).padding(end = 16.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_blame_popularity),
                contentDescription = "popularity",
                tint = Color.Red
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = popularity.toString()
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun RateSectionPrev() {
    MovieAppTheme {
        RateSection(
            voteCount = 200,
            voteAverage = 5.5,
            popularity = 20.7558)
    }

}