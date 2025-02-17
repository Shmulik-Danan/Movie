package com.shmulik.riversidemovie.ui.page.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shmulik.domain.entities.PreViewMovieEntity
import com.shmulik.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel(), Contract.ViewModel {

    override val searchUiState = MutableStateFlow<FavoriteUiState>(FavoriteUiState.Loading)
    override val movies = MutableStateFlow(PagingData.empty<PreViewMovieEntity>())


    override val navigationState = MutableSharedFlow<FavoriteNavigationState>()


    init {
        viewModelScope.launch {
            getFavoriteMovies()
        }
    }


    private suspend fun getFavoriteMovies() {

        repository.favoriteMovies(30)
            .cachedIn(viewModelScope)
            .collect { pagingData ->
                searchUiState.update { FavoriteUiState.SuccessLoading }
                movies.emit(pagingData)
            }
    }

    override fun goToMovieDetails(movie: PreViewMovieEntity) {

    }

}