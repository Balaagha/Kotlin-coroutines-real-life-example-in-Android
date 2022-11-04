package com.example.androidimpltemplate.data.feature.movie.repository

import com.example.androidimpltemplate.data.database.feature.movies.model.CachedMovieModel
import com.example.androidimpltemplate.data.database.feature.movies.source.MovieLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesDataRepositoryImpl @Inject constructor(
    private val movieLocalDataSource: MovieLocalDataSource
) : MoviesDataRepository {
    override suspend fun saveMovies(movies: List<CachedMovieModel>) {
        movieLocalDataSource.saveMovies(movies)
    }

    override suspend fun deleteAllMovies() {
        movieLocalDataSource.deleteAllMovies()
    }

    override suspend fun getFavoriteMovies(): Flow<List<CachedMovieModel>> {
        return movieLocalDataSource.getFavoriteMovies()
    }

    override suspend fun getPopularMovies(): Flow<List<CachedMovieModel>> {
        return movieLocalDataSource.getPopularMovies()
    }

    override suspend fun setFavorite(id: Int) {
        movieLocalDataSource.setFavorite(id)
    }

    override suspend fun removeFavorite(id: Int) {
        movieLocalDataSource.removeFavorite(id)
    }

    override suspend fun getMovie(id: Int): Flow<CachedMovieModel> {
        return movieLocalDataSource.getMovie(id)
    }


}
