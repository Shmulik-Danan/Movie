package com.shmulik.data.db.movies

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shmulik.data.entities.MovieDbData


@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun movies(): PagingSource<Int, MovieDbData>


    @Query("SELECT * FROM movies WHERE id = :movieId")
    suspend fun getMovie(movieId: String): MovieDbData?


    @Query("SELECT * FROM movies WHERE isFavorite = 1")
    fun favoriteMovies(): PagingSource<Int, MovieDbData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<MovieDbData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(movie: MovieDbData)

    @Query("DELETE FROM movies WHERE id=:movieId")
    suspend fun remove(movieId: String)

}