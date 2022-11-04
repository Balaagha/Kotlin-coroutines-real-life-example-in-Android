package com.example.androidimpltemplate.data.database.di

import com.example.androidimpltemplate.data.database.feature.cast.source.CastResponseDataSource
import com.example.androidimpltemplate.data.database.feature.cast.source.CastResponseDataSourceImpl
import com.example.androidimpltemplate.data.database.feature.movies.source.MovieLocalDataSource
import com.example.androidimpltemplate.data.database.feature.movies.source.MovieLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindDataSourceModule {
    @Singleton
    @Binds
    abstract fun bindMovieLocalDataSource(contactListLocalDataSource: MovieLocalDataSourceImpl): MovieLocalDataSource

    @Singleton
    @Binds
    abstract fun bindCastResponseDataSource(contactListLocalDataSource: CastResponseDataSourceImpl): CastResponseDataSource
}