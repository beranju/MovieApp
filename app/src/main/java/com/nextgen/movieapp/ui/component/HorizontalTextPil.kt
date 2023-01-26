package com.nextgen.movieapp.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nextgen.movieapp.ui.theme.MovieAppTheme

@Composable
fun HorizontalTextPil(
    label: String,
    input: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(4.dp))
) {
    Row (
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ){
        Text(
            text = label,
            color = Color.DarkGray,
            modifier = modifier.weight(1f)
        )
        Text(
            text = input,
            fontWeight = FontWeight.Bold,
            modifier = modifier.weight(3f, true)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HorizontalTextPilPreview() {
    MovieAppTheme {
        HorizontalTextPil(label = "Tahun", input = "2020")
    }

}