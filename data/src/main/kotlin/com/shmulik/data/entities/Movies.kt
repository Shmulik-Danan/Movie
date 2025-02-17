package com.shmulik.data.entities

import com.google.gson.annotations.SerializedName

data class Movies(
    @SerializedName("Search") val movies: List<MovieData>,
    @SerializedName("totalResults") val totalResults: String,
    @SerializedName("Response") val response: String
)

fun Movies.isResponseTrue() = response == "True"
