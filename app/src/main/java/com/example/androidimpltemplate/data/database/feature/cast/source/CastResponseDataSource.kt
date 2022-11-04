package com.example.androidimpltemplate.data.database.feature.cast.source

import com.example.androidimpltemplate.data.database.feature.cast.model.CastItem
import com.example.androidimpltemplate.data.database.feature.cast.model.CachedCastResponse

interface CastResponseDataSource {
    suspend fun saveCast(cast: List<CachedCastResponse>)
    suspend fun getCastDetails(id: Int): List<CastItem>
}