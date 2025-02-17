package com.shmulik.riversidemovie.ui.navigaion


import com.shmulik.domain.entities.MovieEntity
import com.shmulik.domain.entities.PreViewMovieEntity
import kotlinx.serialization.Serializable

object Route {

    @Serializable
    object MainMovieRoute

    @Serializable
    object SearchRoute

    @Serializable
    object FavoriteRoute

    @Serializable
    data class MovieDetailsRoute(
        val title: String,
        val year: String,
        val id: String,
        val type: String,
        val urlPic: String
    ){
        companion object {
            fun PreViewMovieEntity.toMovieDetailsRoute() = MovieDetailsRoute(
                title = title,
                year = year,
                id = id,
                type = type,
                urlPic = urlPic
            )
            fun MovieDetailsRoute.toMovieEntity() = MovieEntity(
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
        }

    }



}

sealed class Graph {
    @Serializable
    data object Main : Graph()
}