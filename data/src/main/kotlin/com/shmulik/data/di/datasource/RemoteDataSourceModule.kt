package com.shmulik.data.di.datasource


import com.shmulik.data.repository.movie.MovieDataSource
import com.shmulik.data.repository.movie.MovieRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataSourceModule {

    @Binds
    @Singleton
    fun bindsRemoteMovieDataSource(impl: MovieRemoteDataSource): MovieDataSource.Remote
}