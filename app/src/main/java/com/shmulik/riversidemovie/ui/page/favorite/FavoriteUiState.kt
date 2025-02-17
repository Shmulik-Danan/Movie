package com.shmulik.riversidemovie.ui.page.favorite

import com.shmulik.domain.entities.PreViewMovieEntity


sealed class FavoriteUiState {
    data object Error : FavoriteUiState()
    data object Loading : FavoriteUiState()
    data object SuccessLoading : FavoriteUiState()

}

sealed class FavoriteNavigationState {
    data class MovieDetails(val movie: PreViewMovieEntity) : FavoriteNavigationState()
}