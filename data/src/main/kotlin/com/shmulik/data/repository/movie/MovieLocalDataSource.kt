package com.shmulik.data.repository.movie

import androidx.paging.PagingSource
import com.shmulik.data.db.movies.MovieDao
import com.shmulik.data.entities.MovieData
import com.shmulik.data.entities.MovieDbData
import com.shmulik.data.entities.Movies
import com.shmulik.data.entities.toMovieDbData
import com.shmulik.domain.entities.MovieEntity
import com.shmulik.domain.util.DataResult
import javax.inject.Inject

class MovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) : MovieDataSource.Local {


    override suspend fun getMovie(): DataResult<MovieData> {
        TODO()
    }

    override suspend fun search(): DataResult<Movies> {
        TODO()
    }

    override suspend fun checkFavoriteStatus(movieId: String): DataResult<Boolean> {
        return DataResult.Success(movieDao.getMovie(movieId) != null)
    }

    override suspend fun addMovieToFavorite(movieEntity: MovieEntity) {
        movieDao.add(movieEntity.toMovieDbData())
    }

    override suspend fun removeMovieFromFavorite(movieEntity: MovieEntity) {
        movieDao.remove(movieEntity.id)
    }

    override fun favoriteMovies(): PagingSource<Int, MovieDbData> = movieDao.favoriteMovies()

}

