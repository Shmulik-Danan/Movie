package com.shmulik.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shmulik.domain.entities.MovieEntity
import com.shmulik.domain.entities.PreViewMovieEntity


@Entity(tableName = "movies")
data class MovieDbData(
    @PrimaryKey val id: String,
    val title: String,
    val year: String,
    val urlPic: String,
    val imdbRating: String,
    val type: String,
    val isFavorite: Boolean = true
)

fun MovieDbData.toPreViewMovieEntity() = PreViewMovieEntity(
        id = id,
        title = title,
        year = year,
        urlPic = urlPic,
        type = type
    )

fun MovieDbData.toMovieEntity() = MovieEntity(
    title = title,
    year = year,
    rated = "",
    released = "",
    runtime = "",
    genre = "",
    director = "",
    writer = "",
    actors = "",
    plot = "",
    language = "",
    country = "",
    awards = "",
    poster = urlPic,
    ratings = emptyList(),
    metascore = "",
    imdbRating = "",
    imdbVotes = "",
    id = id,
    type = type,
    dvd = "",
    boxOffice = "",
    production = "",
    website = "",
    response = ""
)

fun MovieEntity.toMovieDbData() = MovieDbData(
    id = id,
    title = title,
    year = year,
    urlPic = poster,
    imdbRating = imdbRating,
    type = type
)