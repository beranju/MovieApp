package com.nextgen.movieapp.ui.component

import android.support.v4.os.IResultReceiver.Default
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nextgen.movieapp.R
import com.nextgen.movieapp.ui.theme.MovieAppTheme
import com.nextgen.movieapp.ui.theme.Shapes

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    message: String,
    action: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .size(300.dp)
            .clip(RoundedCornerShape(16.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_error_64) ,
            contentDescription = null,
            modifier.fillMaxWidth()
                .height(200.dp)
                .padding(16.dp)
                .clip(Shapes.medium)
        )
        Text(
            text = if (message == "") "Something wrong" else message,
            textAlign = TextAlign.Center,
            maxLines = 3,
        )
        Button(onClick = { action() }) {
            Text(text = "reload")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAppTheme {
        ErrorView(message = "something wrong", action = {})
    }

}