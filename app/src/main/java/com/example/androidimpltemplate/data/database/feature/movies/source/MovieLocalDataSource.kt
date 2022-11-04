package com.example.androidimpltemplate.data.database.feature.movies.source

import com.example.androidimpltemplate.data.database.feature.movies.model.CachedMovieModel
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    suspend fun saveMovies(movies: List<CachedMovieModel>)
    suspend fun deleteAllMovies()
    suspend fun getFavoriteMovies(): Flow<List<CachedMovieModel>>
    suspend fun getMovie(id: Int): Flow<CachedMovieModel>
    suspend fun getPopularMovies(): Flow<List<CachedMovieModel>>
    suspend fun setFavorite(id: Int)
    suspend fun removeFavorite(id: Int)
}