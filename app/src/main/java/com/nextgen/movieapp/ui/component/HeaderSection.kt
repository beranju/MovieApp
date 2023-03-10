package com.nextgen.movieapp.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import com.nextgen.movieapp.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nextgen.movieapp.ui.navigation.Screen
import com.nextgen.movieapp.ui.theme.Alice200
import com.nextgen.movieapp.ui.theme.MovieAppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HeaderSection(
    modifier: Modifier = Modifier,
    querySearch: String,
    onClearClick: () -> Unit,
    onSearchTextChanged: (String) -> Unit = {},
    navController: NavHostController,
) {
    var showClearButton by remember { mutableStateOf(false) }
    val keyBoardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column (
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))
            .background(MaterialTheme.colors.primary)
            .padding(bottom = 16.dp),
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(60.dp)
            ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.welcome_text),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.surface,
                modifier = Modifier.weight(2f, true)
            )
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "favorite",
                modifier = Modifier
                    .clickable {
                    navController.navigate(Screen.FAVORITE.route) },
                tint = MaterialTheme.colors.surface
            )
            Spacer(modifier = Modifier.width(20.dp))
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "about_page",
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.ABOUT.route) },
                tint = MaterialTheme.colors.surface
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = querySearch,
            onValueChange = onSearchTextChanged,
            placeholder = {
                Text(text = stringResource(id = R.string.hint_label))

            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
            trailingIcon = {
                AnimatedVisibility(
                    visible = showClearButton,
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    IconButton(onClick = {
                        onClearClick()
                        focusManager.clearFocus()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close ,
                            contentDescription = null)
                    }
                }
            },
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            maxLines = 1,
            keyboardActions =
                KeyboardActions(
                    onDone = {
                        keyBoardController?.hide()
                    }
                )
            ,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    showClearButton = focusState.isFocused
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderSectionPreview() {
    MovieAppTheme {
        HeaderSection(querySearch = "", onClearClick = {}, onSearchTextChanged = {}, navController = rememberNavController()
        )
    }

}