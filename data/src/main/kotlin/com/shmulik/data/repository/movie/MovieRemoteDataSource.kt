package com.shmulik.data.repository.movie

import com.shmulik.data.api.MovieApi
import com.shmulik.data.entities.MovieData
import com.shmulik.data.entities.Movies
import com.shmulik.data.util.safeApiCall
import com.shmulik.domain.util.DataResult
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val movieApi: MovieApi
) : MovieDataSource.Remote {

    override suspend fun getMovie(id: String): DataResult<MovieData> {
        return safeApiCall() {
            movieApi.getMovie(id)
        }

    }

    override suspend fun search(query: String, page: Int): DataResult<Movies> {
        return safeApiCall() {
            movieApi.search(query, page)
        }
    }
}