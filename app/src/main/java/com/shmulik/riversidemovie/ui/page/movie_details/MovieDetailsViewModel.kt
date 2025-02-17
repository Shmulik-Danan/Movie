package com.shmulik.riversidemovie.ui.page.movie_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.shmulik.domain.repository.MovieRepository
import com.shmulik.domain.util.DataResult
import com.shmulik.riversidemovie.ui.navigaion.Route
import com.shmulik.riversidemovie.ui.navigaion.Route.MovieDetailsRoute.Companion.toMovieEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: MovieRepository
) : ViewModel(), MovieDetailsContract.ViewModel {


    override val movieDetailUiState = MutableStateFlow(MovieDetailUiState())


    init {
        val movieEntity = savedStateHandle.toRoute<Route.MovieDetailsRoute>().toMovieEntity()

        movieDetailUiState.update {
            it.copy(
                isLoading = false, movieEntity = movieEntity
            )
        }

        getMovie(movieEntity.id)

    }

    private fun getMovie(id: String) {

        combine(
            repository.getMovie(id),
            repository.checkFavoriteStatus(id)
        ) { movieResult, isFavorite ->
            when (movieResult) {
                is DataResult.Success -> {
                    movieDetailUiState.update {
                        it.copy(
                            isLoading = false,
                            movieEntity = movieResult.data,
                            isFavorite = when (isFavorite) {
                                is DataResult.Success -> isFavorite.data
                                else -> false
                            }
                        )
                    }
                }

                is DataResult.Error -> {
                    movieDetailUiState.update {
                        it.copy(
                            isLoading = false,
                            error = movieResult.error.message,
                            isFavorite = false
                        )
                    }
                }
            }
        }.onEach { }
            .launchIn(viewModelScope)
    }


    override fun onFavoriteClicked() {
        viewModelScope.launch {
            val item= movieDetailUiState.value
            item.movieEntity?:return@launch

            if(item.isFavorite){
                repository.removeMovieFromFavorite(item.movieEntity)
            }else{
                repository.addMovieToFavorite(item.movieEntity)
            }

            movieDetailUiState.update { it.copy(isFavorite = !item.isFavorite) }
        }
    }


}