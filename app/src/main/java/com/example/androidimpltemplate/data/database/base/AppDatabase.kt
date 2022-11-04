package com.example.androidimpltemplate.data.database.base

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.androidimpltemplate.data.database.feature.cast.dao.CastResponseDao
import com.example.androidimpltemplate.data.database.feature.cast.model.CachedCastResponse
import com.example.androidimpltemplate.data.database.feature.movies.dao.MoviesDao
import com.example.androidimpltemplate.data.database.feature.movies.model.CachedMovieModel

@Database(
    entities = [
        CachedMovieModel::class,
        CachedCastResponse::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao
    abstract fun castResponseDao(): CastResponseDao
}