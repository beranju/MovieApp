package com.nextgen.movieapp.ui.component

import android.provider.Telephony.Mms.Rate
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nextgen.movieapp.ui.theme.MovieAppTheme

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
            .padding(8.dp)
            .heightIn(min = 80.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.weight(1f)) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "vote average",
                tint = Color.Yellow
            )
            Text(
                text = voteAverage.toString()
            )
        }
        Row(modifier = Modifier.weight(1f)) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "vote count",
                tint = MaterialTheme.colors.primary
            )
            Text(
                text = voteCount.toString()
            )
        }
        Row(modifier = Modifier.weight(1f)) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "popularity",
                tint = Color.Red
            )
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