package com.shmulik.riversidemovie.ui.page.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.shmulik.domain.entities.PreViewMovieEntity
import com.shmulik.riversidemovie.ui.navigaion.Route.MovieDetailsRoute.Companion.toMovieDetailsRoute
import com.shmulik.riversidemovie.ui.widget.MoviesList
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf


@Composable
fun FavoritePage(
    viewModel: FavoriteViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val searchUiState by viewModel.searchUiState.collectAsState()
    val movies = viewModel.movies.collectAsLazyPagingItems()


    LaunchedEffect(key1 = Unit) {
        viewModel.navigationState.collectLatest { navState ->
            when (navState) {
                is FavoriteNavigationState.MovieDetails -> navController.navigate(
                    navState.movie.toMovieDetailsRoute()
                )
            }
        }
    }



    Scaffold(
        modifier = Modifier.fillMaxSize(),

        ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.White)
        ) {

            FavoriteScreen(
                searchUiState = searchUiState,
                movies = movies,
                onMovieClick = viewModel::goToMovieDetails,
            )
        }
    }
}

@Composable
fun FavoriteScreen(
    searchUiState: FavoriteUiState,
    movies: LazyPagingItems<PreViewMovieEntity>,
    onMovieClick: (PreViewMovieEntity) -> Unit
) {
    Column {

        when (searchUiState) {
            FavoriteUiState.Error -> {
                // Error
            }

            FavoriteUiState.Loading -> {
                AddLoading()
            }

            is FavoriteUiState.SuccessLoading -> {
                MoviesList(
                    movies = movies,
                    onMovieClick = onMovieClick
                )
            }
        }
    }


}

@Composable
private fun AddLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}


@Preview(showBackground = true)
@Composable
private fun SearchViewModelPreview() {
    val fakeMovies = listOf(
        PreViewMovieEntity(
            id = "1", title = "Inception", year = "2010", urlPic = "", type = "movie"
        ),
        PreViewMovieEntity(
            id = "2", title = "Interstellar", year = "2014", urlPic = "", type = "movie"
        ),
        PreViewMovieEntity(
            id = "3", title = "Interstellar", year = "2014", urlPic = "", type = "movie"
        )
    )

    FavoriteScreen(
        searchUiState = FavoriteUiState.SuccessLoading,
        movies = flowOf(PagingData.from(fakeMovies)).collectAsLazyPagingItems(),
        onMovieClick = {},
    )
}

