package com.example.androidimpltemplate.data.feature.movie.repository

import com.example.androidimpltemplate.data.database.feature.cast.model.CastItem
import com.example.androidimpltemplate.data.database.feature.cast.model.CachedCastResponse
import com.example.androidimpltemplate.data.database.feature.cast.source.CastResponseDataSource
import javax.inject.Inject

class CastDataRepositoryImpl @Inject constructor(
    private val castResponseDataSource: CastResponseDataSource
) : CastDataRepository {
    override suspend fun saveCast(cast: List<CachedCastResponse>) {
        castResponseDataSource.saveCast(cast)
    }

    override suspend fun getCastDetails(id: Int): List<CastItem> {
        return castResponseDataSource.getCastDetails(id)
    }
}