package com.shmulik.data.di.datasource


import com.shmulik.data.repository.movie.MovieDataSource
import com.shmulik.data.repository.movie.MovieLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LocalDataSourceModule {

    @Binds
    @Singleton
    fun bindsRemoteMovieDataSource(impl: MovieLocalDataSource): MovieDataSource.Local
}