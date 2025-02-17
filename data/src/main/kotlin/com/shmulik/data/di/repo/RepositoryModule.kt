package com.shmulik.data.di.repo

import com.shmulik.data.repository.movie.MovieRepositoryImpl
import com.shmulik.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsMovieRepository(impl: MovieRepositoryImpl): MovieRepository
}