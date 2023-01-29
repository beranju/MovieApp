package com.nextgen.movieapp.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nextgen.movieapp.R
import com.nextgen.movieapp.ui.theme.MovieAppTheme

@Composable
fun ActionBarTemplate(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    title: String,
) {
    Row(
        modifier = modifier.height(50.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            navigateBack()
        },
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back_button_cd),
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .padding(start = 20.dp)
                .weight(3f, true)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ActionBarPreview() {
    MovieAppTheme {
        ActionBarTemplate(navigateBack = {}, title = "example")
    }
}