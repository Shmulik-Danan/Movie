package com.shmulik.riversidemovie.ui.page.movie_details

import kotlinx.coroutines.flow.StateFlow

interface MovieDetailsContract {
    interface ViewModel {
        val movieDetailUiState: StateFlow<MovieDetailUiState>
        fun onFavoriteClicked()

    }
}