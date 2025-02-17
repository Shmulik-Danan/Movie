package com.shmulik.riversidemovie.ui.page.movie_details

import com.shmulik.domain.entities.MovieEntity


data class MovieDetailUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val movieEntity: MovieEntity? = null,
    val isFavorite: Boolean = false,
)