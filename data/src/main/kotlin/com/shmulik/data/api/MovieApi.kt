package com.shmulik.data.api

import com.shmulik.data.entities.MovieData
import com.shmulik.data.entities.Movies
import com.shmulik.data.interceptor.offline.SpecificOffline
import com.shmulik.data.interceptor.offline.SpecificOfflineState
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApi {

    @GET("/")
    @SpecificOffline(cachingMode = SpecificOfflineState.OFFLINE)
    suspend fun search(
        @Query("s") query: String,
        @Query("page") page: Int
    ): Movies

    @GET("/")
    suspend fun getMovie(
        @Query("i") id: String
    ): MovieData
}