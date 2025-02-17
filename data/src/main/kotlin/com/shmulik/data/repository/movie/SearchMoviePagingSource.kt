package com.shmulik.data.repository.movie

import com.shmulik.data.entities.toDomain
import com.shmulik.domain.entities.MovieEntity
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shmulik.data.entities.isResponseTrue
import com.shmulik.data.entities.toPreViewMovieEntity
import com.shmulik.domain.entities.PreViewMovieEntity
import com.shmulik.domain.util.DataResult

private const val STARTING_PAGE_INDEX = 1

class SearchMoviePagingSource(
    private val query: String,
    private val remote: MovieDataSource.Remote
) : PagingSource<Int, PreViewMovieEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PreViewMovieEntity> {
        val page = params.key ?: STARTING_PAGE_INDEX


        return when (val result = remote.search(query, page)) {
            is DataResult.Success -> {
                when (result.data.isResponseTrue()) {
                    false -> LoadResult.Error(Exception("No more data"))
                    true -> {
                        LoadResult.Page(
                            data = result.data.movies.map { it.toPreViewMovieEntity() },
                            prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                            nextKey = if (result.data.movies.isEmpty()) null else page + 1
                        )

                    }
                }

            }

            is DataResult.Error -> LoadResult.Error(result.error)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PreViewMovieEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}