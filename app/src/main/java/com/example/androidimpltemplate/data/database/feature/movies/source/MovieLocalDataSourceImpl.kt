package com.example.androidimpltemplate.data.database.feature.movies.source

import com.example.androidimpltemplate.data.database.feature.movies.dao.MoviesDao
import com.example.androidimpltemplate.data.database.feature.movies.model.CachedMovieModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val moviesDao: MoviesDao,
) : MovieLocalDataSource {
    override suspend fun saveMovies(movies: List<CachedMovieModel>) {
        moviesDao.saveAllMovies(movies)
    }

    override suspend fun deleteAllMovies() {
        moviesDao.deleteAllMovies()
    }

    override suspend fun getFavoriteMovies(): Flow<List<CachedMovieModel>> {
        return moviesDao.getFavoriteMovies()
    }

    override suspend fun getMovie(id: Int): Flow<CachedMovieModel> {
        return moviesDao.getMovie(id)
    }

    override suspend fun getPopularMovies(): Flow<List<CachedMovieModel>> {
        return moviesDao.getPopularMovies()
    }

    override suspend fun setFavorite(id: Int) {
        moviesDao.setFavorite(id)
    }

    override suspend fun removeFavorite(id: Int) {
        moviesDao.removeFavorite(id)
    }


}