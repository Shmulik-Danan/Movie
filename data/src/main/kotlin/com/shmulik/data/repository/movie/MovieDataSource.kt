package com.shmulik.data.repository.movie

import androidx.paging.PagingSource
import com.shmulik.data.entities.MovieData
import com.shmulik.data.entities.MovieDbData
import com.shmulik.data.entities.Movies
import com.shmulik.domain.entities.MovieEntity
import com.shmulik.domain.util.DataResult

interface MovieDataSource {
    interface Remote {
        suspend fun getMovie(id: String): DataResult<MovieData>

        suspend fun search(query: String, page: Int): DataResult<Movies>

    }

    interface Local {
        suspend fun getMovie(): DataResult<MovieData>

        suspend fun search(): DataResult<Movies>

        suspend fun checkFavoriteStatus(movieId: String): DataResult<Boolean>

        suspend fun addMovieToFavorite(movieEntity: MovieEntity)

        suspend fun removeMovieFromFavorite(movieEntity: MovieEntity)

        fun favoriteMovies(): PagingSource<Int, MovieDbData>

    }
}