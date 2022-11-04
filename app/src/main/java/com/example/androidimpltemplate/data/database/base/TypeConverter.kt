package com.example.androidimpltemplate.data.database.base

import androidx.room.TypeConverter
import com.example.androidimpltemplate.data.database.feature.cast.model.CastItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {
    @TypeConverter
    fun fromCast(cast: List<CastItem>): String {
        return Gson().toJson(cast)
    }

    @TypeConverter
    fun toCast(cast: String?): List<CastItem> {
        val listType = object : TypeToken<List<CastItem?>?>() {}.type
        return try {
            Gson().fromJson(cast, listType)
        } catch (error: Error) {
            emptyList()
        }
    }
}