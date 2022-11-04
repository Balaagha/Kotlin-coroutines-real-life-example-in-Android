package com.example.androidimpltemplate.data.database.feature.movies.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidimpltemplate.data.database.base.BaseDao
import com.example.androidimpltemplate.data.database.feature.movies.model.CachedMovieModel
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MoviesDao: BaseDao<CachedMovieModel> {

  @Query("SELECT * FROM cached_movie_table")
  abstract fun getPopularMovies(): Flow<List<CachedMovieModel>>

  @Query("SELECT * FROM cached_movie_table WHERE isFavorite = 1")
  abstract fun getFavoriteMovies(): Flow<List<CachedMovieModel>>

  @Query("SELECT * FROM cached_movie_table WHERE id =:id LIMIT 1")
  abstract fun getMovie(id: Int): Flow<CachedMovieModel>

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  abstract suspend fun saveAllMovies(movies: List<CachedMovieModel>)

  @Query("DELETE FROM cached_movie_table")
  abstract suspend fun deleteAllMovies()

  @Query("UPDATE cached_movie_table SET isFavorite = 1 WHERE id=:id")
  abstract suspend fun setFavorite(id: Int)

  @Query("UPDATE cached_movie_table SET isFavorite = 0 WHERE id=:id")
  abstract suspend fun removeFavorite(id: Int)

}
