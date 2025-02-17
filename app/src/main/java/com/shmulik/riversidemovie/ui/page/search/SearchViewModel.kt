package com.shmulik.riversidemovie.ui.page.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shmulik.domain.entities.MovieEntity
import com.shmulik.domain.entities.PreViewMovieEntity
import com.shmulik.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel(), Contract.ViewModel {

    override val searchUiState = MutableStateFlow<SearchUiState>(SearchUiState.Loading)
    override val movies = MutableStateFlow(PagingData.empty<PreViewMovieEntity>())
    override val query = MutableStateFlow("")
    override val navigationState = MutableSharedFlow<SearchNavigationState>()
    override val selectedMovie = MutableStateFlow<PreViewMovieEntity>(PreViewMovieEntity())


    override fun onQueryChange(text: String) {
        query.value = text
    }

    override fun doSearch(query: String) {
        viewModelScope.launch {
            if (query.length > 3)
                search(query)
        }
    }

    private suspend fun search(query: String) {
        repository.search(query, 10)
            .cachedIn(viewModelScope)
            .collect { pagingData ->
                searchUiState.update { SearchUiState.SuccessLoading }
                movies.emit(pagingData)
            }
    }

    override fun goToMovieDetails(movie: PreViewMovieEntity) {
        viewModelScope.launch {
            navigationState.emit(SearchNavigationState.MovieDetails(movie))
        }
    }

    override fun setSelectedMovie(movie: PreViewMovieEntity) {
        selectedMovie.value = movie
    }

    override fun goToFavorite() {
        viewModelScope.launch {
            navigationState.emit(SearchNavigationState.Favorite)
        }
    }
}