package com.shmulik.riversidemovie.ui.page.search

import com.shmulik.domain.entities.PreViewMovieEntity


sealed class SearchUiState {
    data object Error : SearchUiState()
    data object Loading : SearchUiState()
    data object SuccessLoading : SearchUiState()

}

sealed class SearchNavigationState {
    data class MovieDetails(val movie: PreViewMovieEntity) : SearchNavigationState()
    data object Favorite : SearchNavigationState()
}