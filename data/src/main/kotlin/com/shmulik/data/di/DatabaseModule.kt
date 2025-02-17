package com.shmulik.data.di

import android.content.Context
import androidx.room.Room
import com.shmulik.data.db.movies.MovieDao
import com.shmulik.data.db.movies.MovieDatabase
import com.shmulik.data.util.DiskExecutor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDiskExecutor(): DiskExecutor {
        return DiskExecutor()
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(
        @ApplicationContext context: Context,
        diskExecutor: DiskExecutor
    ): MovieDatabase {
        return Room
            .databaseBuilder(context, MovieDatabase::class.java, "movie.db")
            .setQueryExecutor(diskExecutor)
            .setTransactionExecutor(diskExecutor)
            .build()
    }

    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.movieDao()
    }

}