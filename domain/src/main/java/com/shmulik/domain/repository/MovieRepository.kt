package com.shmulik.domain.repository

import androidx.paging.PagingData
import com.shmulik.domain.entities.MovieEntity
import com.shmulik.domain.entities.PreViewMovieEntity
import com.shmulik.domain.util.DataResult
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    fun getMovie(id: String): Flow<DataResult<MovieEntity>>
    suspend fun search(query: String, page: Int): Flow<PagingData<PreViewMovieEntity>>
    fun checkFavoriteStatus(movieId: String): Flow<DataResult<Boolean>>
    suspend fun addMovieToFavorite(movieEntity: MovieEntity)
    suspend fun removeMovieFromFavorite(movieEntity: MovieEntity)
    fun favoriteMovies(pageSize: Int): Flow<PagingData<PreViewMovieEntity>>

}