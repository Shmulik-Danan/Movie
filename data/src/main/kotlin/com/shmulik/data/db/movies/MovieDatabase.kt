package com.shmulik.data.db.movies

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shmulik.data.entities.MovieDbData


@Database(
    entities = [MovieDbData::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

}