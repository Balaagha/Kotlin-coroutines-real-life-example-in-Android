package com.example.androidimpltemplate.data.feature.movie.repository

import com.example.androidimpltemplate.data.database.feature.cast.model.CastItem
import com.example.androidimpltemplate.data.database.feature.cast.model.CachedCastResponse

interface CastDataRepository {
    suspend fun saveCast(cast: List<CachedCastResponse>)
    suspend fun getCastDetails(id: Int): List<CastItem>
}