package com.shmulik.riversidemovie.ui.page.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.shmulik.domain.entities.PreViewMovieEntity
import com.shmulik.riversidemovie.ui.navigaion.Route
import com.shmulik.riversidemovie.ui.navigaion.Route.MovieDetailsRoute.Companion.toMovieDetailsRoute
import com.shmulik.riversidemovie.ui.widget.MoviesList
import com.shmulik.riversidemovie.ui.widget.SearchView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf


@Composable
fun SearchPage(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val searchUiState by viewModel.searchUiState.collectAsState()
    val movies = viewModel.movies.collectAsLazyPagingItems()
    val selectedMovie by viewModel.selectedMovie.collectAsState()



    LaunchedEffect(key1 = Unit) {
        viewModel.navigationState.collectLatest { navState ->
            when (navState) {
                is SearchNavigationState.MovieDetails -> navController.navigate(
                    navState.movie.toMovieDetailsRoute()
                )

                SearchNavigationState.Favorite -> navController.navigate(
                    Route.FavoriteRoute
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

            SearchScreen(
                searchUiState = searchUiState,
                movies = movies,
                selectedMovie = selectedMovie,
                onQueryChange = viewModel::onQueryChange,
                onMovieClick = {
                    viewModel.goToMovieDetails(it)
                    viewModel.setSelectedMovie(it)

                },
                onSearchClick = viewModel::doSearch,
                onGoToFavoriteClick = viewModel::goToFavorite

            )
        }
    }
}

@Composable
fun SearchScreen(
    searchUiState: SearchUiState,
    movies: LazyPagingItems<PreViewMovieEntity>,
    onQueryChange: (String) -> Unit,
    onSearchClick: (String) -> Unit,
    onMovieClick: (PreViewMovieEntity) -> Unit,
    selectedMovie: PreViewMovieEntity,
    onGoToFavoriteClick: () -> Unit
) {
    Column {
        AddTextGoToFavorite(onGoToFavoriteClick = onGoToFavoriteClick)

        AddSearch(onQueryChange = onQueryChange, onSearchClick = onSearchClick)

        when (searchUiState) {
            SearchUiState.Error -> {
                // Error
            }

            SearchUiState.Loading -> {
                AddLoading()
            }

            is SearchUiState.SuccessLoading -> {
                MoviesList(
                    movies = movies,
                    selectedMovie = selectedMovie,
                    onMovieClick = onMovieClick
                )
            }
        }
    }


}

@Composable
fun AddTextGoToFavorite(onGoToFavoriteClick: () -> Unit) {

    Text(modifier = Modifier
        .clickable {
            onGoToFavoriteClick()
        }
        .padding(16.dp), text = "Go to Favorite", color = Color.Black)
}

@Composable
fun AddSearch(
    onQueryChange: (String) -> Unit,
    onSearchClick: (String) -> Unit
) {
    SearchView(onQueryChange = onQueryChange::invoke, onSearchClick = onSearchClick::invoke)
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

    SearchScreen(
        searchUiState = SearchUiState.SuccessLoading,
        movies = flowOf(PagingData.from(fakeMovies)).collectAsLazyPagingItems(),
        onQueryChange = {},
        onSearchClick = {},
        onMovieClick = {},
        selectedMovie = PreViewMovieEntity(),
        {}
    )
}

