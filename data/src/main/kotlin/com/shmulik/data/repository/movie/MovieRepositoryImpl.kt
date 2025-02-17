package com.shmulik.data.repository.movie


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.shmulik.data.entities.toDomain
import com.shmulik.data.entities.toMovieEntity
import com.shmulik.data.entities.toPreViewMovieEntity
import com.shmulik.domain.entities.MovieEntity
import com.shmulik.domain.entities.PreViewMovieEntity
import com.shmulik.domain.repository.MovieRepository
import com.shmulik.domain.util.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieDataSource.Remote,
    private val localDataSource: MovieDataSource.Local

) : MovieRepository {

    override fun getMovie(id: String): Flow<DataResult<MovieEntity>> {
        return flow {
            remoteDataSource.getMovie(id).let { result ->
                when (result) {
                    is DataResult.Success -> {
                        emit(DataResult.Success(result.data.toMovieEntity()))
                    }

                    is DataResult.Error -> {
                        emit(DataResult.Error(result.error))
                    }
                }
            }
        }
    }


    override suspend fun search(query: String, page: Int): Flow<PagingData<PreViewMovieEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { SearchMoviePagingSource(query, remoteDataSource) }
        ).flow
    }


    override fun checkFavoriteStatus(movieId: String): Flow<DataResult<Boolean>> {
        return flow {
            emit(localDataSource.checkFavoriteStatus(movieId))
        }
    }

    override suspend fun addMovieToFavorite(movieEntity: MovieEntity) {
        localDataSource.addMovieToFavorite(movieEntity)
    }

    override suspend fun removeMovieFromFavorite(movieEntity: MovieEntity) {
        localDataSource.removeMovieFromFavorite(movieEntity)
    }

    override fun favoriteMovies(pageSize: Int): Flow<PagingData<PreViewMovieEntity>> = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { localDataSource.favoriteMovies() }
    ).flow.map { pagingData ->
        pagingData.map { it.toPreViewMovieEntity() }
    }

}