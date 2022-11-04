package com.example.androidimpltemplate.data.database.feature.cast.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidimpltemplate.data.database.base.BaseDao
import com.example.androidimpltemplate.data.database.feature.cast.model.CachedCastResponse

@Dao
abstract class CastResponseDao:BaseDao<CachedCastResponse> {

  @Query("SELECT * FROM CACHED_CAST_TABLE WHERE :id = id")
  abstract fun getCastDetails(id: Int): CachedCastResponse

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract fun saveCast(cast: List<CachedCastResponse>)
}