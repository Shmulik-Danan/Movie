package com.shmulik.riversidemovie.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.shmulik.domain.entities.PreViewMovieEntity
import com.shmulik.riversidemovie.util.centerItem
import kotlinx.coroutines.launch

@Composable
fun MoviesList(
    movies: LazyPagingItems<PreViewMovieEntity>,
    selectedMovie: PreViewMovieEntity = PreViewMovieEntity(),
    onMovieClick: (PreViewMovieEntity) -> Unit
) {
    val listState = rememberLazyListState()

    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        state = listState
    ) {
        items(movies.itemCount) { index ->
            val movie = movies[index] ?: return@items
            MovieCard(
                item = movie,
                modifier = Modifier.clickable {
                    coroutineScope.launch {
                        centerItem(listState, index)
                        onMovieClick(movie)
                    }
                },
                isSelected = (movie.id == selectedMovie.id)
            )
        }
    }
}