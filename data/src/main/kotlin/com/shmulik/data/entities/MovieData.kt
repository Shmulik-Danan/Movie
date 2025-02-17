package com.shmulik.data.entities

import com.google.gson.annotations.SerializedName
import com.shmulik.domain.entities.MovieEntity
import com.shmulik.domain.entities.PreViewMovieEntity


data class MovieData(
    @SerializedName("Title") val title: String = "",
    @SerializedName("Year") val year: String = "",
    @SerializedName("imdbID") val imdbID: String = "",
    @SerializedName("Poster") val poster: String = "",

    @SerializedName("Rated") val rated: String = "",
    @SerializedName("Released") val released: String = "",
    @SerializedName("Runtime") val runtime: String = "",
    @SerializedName("Genre") val genre: String = "",
    @SerializedName("Director") val director: String = "",
    @SerializedName("Writer") val writer: String = "",
    @SerializedName("Actors") val actors: String = "",
    @SerializedName("Plot") val plot: String = "",
    @SerializedName("Language") val language: String = "",
    @SerializedName("Country") val country: String = "",
    @SerializedName("Awards") val awards: String = "",
    @SerializedName("Ratings") val ratings: List<Rating> = emptyList(),
    @SerializedName("Metascore") val metascore: String = "",
    @SerializedName("imdbRating") val imdbRating: String = "",
    @SerializedName("imdbVotes") val imdbVotes: String = "",
    @SerializedName("Type") val type: String = "",
    @SerializedName("DVD") val dvd: String = "",
    @SerializedName("BoxOffice") val boxOffice: String = "",
    @SerializedName("Production") val production: String = "",
    @SerializedName("Website") val website: String = "",
    @SerializedName("Response") val response: String = ""
)

data class Rating(
    @SerializedName("Source") val source: String,
    @SerializedName("Value") val value: String
)

fun MovieData.toPreViewMovieEntity() = PreViewMovieEntity(
    title = title,
    year = year,
    id = imdbID,
    type = type,
    urlPic = poster
)

fun MovieData.toMovieEntity() = MovieEntity(
    title = title,
    year = year,
    rated = rated,
    released = released,
    runtime = runtime,
    genre = genre,
    director = director,
    writer = writer,
    actors = actors,
    plot = plot,
    language = language,
    country = country,
    awards = awards,
    poster = poster,
    ratings = ratings.map { it.toDomain() },
    metascore = metascore,
    imdbRating = imdbRating,
    imdbVotes = imdbVotes,
    id = imdbID,
    type = type,
    dvd = dvd,
    boxOffice = boxOffice,
    production = production,
    website = website,
    response = response
)

fun Rating.toDomain() = com.shmulik.domain.entities.Rating(
    source = source,
    value = value
)