package com.shmulik.riversidemovie.ui.page.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.paging.PagingData
import com.shmulik.domain.entities.MovieEntity
import com.shmulik.domain.entities.PreViewMovieEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface Contract {

    interface ViewModel {
        val searchUiState: StateFlow<SearchUiState>
        val movies: StateFlow<PagingData<PreViewMovieEntity>>
        val query: MutableStateFlow<String>
        val navigationState: SharedFlow<SearchNavigationState>
        val selectedMovie: SharedFlow<PreViewMovieEntity>

        fun onQueryChange(query: String)
        fun doSearch(query: String)
        fun goToMovieDetails(movie: PreViewMovieEntity)
        fun setSelectedMovie(movie: PreViewMovieEntity)
        fun goToFavorite()


    }
}