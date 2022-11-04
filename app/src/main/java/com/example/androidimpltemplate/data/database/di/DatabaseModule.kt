package com.example.androidimpltemplate.data.database.di

import android.content.Context
import androidx.room.Room
import com.example.androidimpltemplate.data.database.base.AppDatabase
import com.example.androidimpltemplate.data.database.utils.DatabaseConstant.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(
        database: AppDatabase,
    ) = database.movieDao()

    @Singleton
    @Provides
    fun provideCastResponseDao(
        database: AppDatabase,
    ) = database.castResponseDao()

}