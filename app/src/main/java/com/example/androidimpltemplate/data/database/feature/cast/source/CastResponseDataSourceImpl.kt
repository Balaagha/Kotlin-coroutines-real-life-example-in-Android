package com.example.androidimpltemplate.data.database.feature.cast.source

import com.example.androidimpltemplate.data.database.feature.cast.dao.CastResponseDao
import com.example.androidimpltemplate.data.database.feature.cast.model.CastItem
import com.example.androidimpltemplate.data.database.feature.cast.model.CachedCastResponse
import javax.inject.Inject


class CastResponseDataSourceImpl @Inject constructor(
    private val castResponseDao: CastResponseDao
): CastResponseDataSource {
    override suspend fun saveCast(cast: List<CachedCastResponse>) {
        castResponseDao.saveCast(cast)
    }

    override suspend fun getCastDetails(id: Int): List<CastItem> {
        return castResponseDao.getCastDetails(id).castItemList
    }

}