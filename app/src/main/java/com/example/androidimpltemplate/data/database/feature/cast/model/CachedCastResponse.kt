package com.example.androidimpltemplate.data.database.feature.cast.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.androidimpltemplate.data.database.base.TypeConverter
import com.example.androidimpltemplate.data.database.utils.DatabaseConstant.CACHED_CAST_TABLE
import com.google.gson.annotations.SerializedName

@Entity(tableName = CACHED_CAST_TABLE)
data class CachedCastResponse(
    @PrimaryKey
    val id: Int,
    @TypeConverters(TypeConverter::class)
    @SerializedName("cast")
    val castItemList: List<CastItem> = emptyList()
)
