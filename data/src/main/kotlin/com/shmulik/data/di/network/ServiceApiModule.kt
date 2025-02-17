package com.shmulik.data.di.network

import com.shmulik.data.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit


@Module
@InstallIn(SingletonComponent::class)
class ServiceApiModule {

    @Provides
    fun providesMovieApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)

}