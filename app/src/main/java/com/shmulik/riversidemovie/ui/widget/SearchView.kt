package com.shmulik.riversidemovie.ui.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun SearchView(
    onQueryChange: (query: String) -> Unit,
    onSearchClick: (query: String) -> Unit
) {
    var query by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current


    Card(
        shape = RoundedCornerShape(90),
        elevation = CardDefaults.cardElevation(9.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row {

            TextField(

                placeholder = {
                    Text(text = "Search")
                },
                value = query,
                onValueChange = {
                    query = it
                    onQueryChange(it)
                },
                trailingIcon = { AddCloseIcon(query = query, onQueryChange = { query = "" }) },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    onSearchClick(query)
                    keyboardController?.hide()
                }),
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent

                )

            )

            AddSearchIcon(query = query, onSearchClick = onSearchClick)

        }

    }
}

@Composable
fun AddCloseIcon(
    query: String,
    onQueryChange: (String) -> Unit
) {
    if (query.isNotEmpty()) {
        IconButton(onClick = {
            onQueryChange("")
        }) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
        }
    }
}


@Composable
fun AddSearchIcon(
    query: String,
    onSearchClick: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    IconButton(
        onClick = {
            onSearchClick(query)
            keyboardController?.hide()
        }
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = ""
        )
    }
}

@Composable
@Preview
fun PreviewSearchView() {
    SearchView(onQueryChange = {}, onSearchClick = {})
}