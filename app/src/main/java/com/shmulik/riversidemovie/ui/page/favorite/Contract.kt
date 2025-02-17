package com.shmulik.riversidemovie.ui.page.favorite

import androidx.paging.PagingData
import com.shmulik.domain.entities.PreViewMovieEntity
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface Contract {

    interface ViewModel {
        val searchUiState: StateFlow<FavoriteUiState>
        val movies: StateFlow<PagingData<PreViewMovieEntity>>
        val navigationState: SharedFlow<FavoriteNavigationState>

        fun goToMovieDetails(movie: PreViewMovieEntity)


    }
}