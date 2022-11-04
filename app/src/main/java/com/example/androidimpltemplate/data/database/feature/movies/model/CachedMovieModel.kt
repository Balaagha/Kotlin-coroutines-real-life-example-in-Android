package com.example.androidimpltemplate.data.database.feature.movies.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidimpltemplate.data.database.utils.DatabaseConstant.CACHED_MOVIE_TABLE
import com.example.androidimpltemplate.data.database.utils.convertToFiveStarScale
import com.example.androidimpltemplate.data.database.utils.toLanguageName
import com.example.androidimpltemplate.data.database.utils.toYear
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = CACHED_MOVIE_TABLE)
@Parcelize
data class CachedMovieModel(
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("vote_average")
    val voteAverage: Float = 0f,
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    @SerializedName("vote_count")
    val voteCount: Int = 0,
    val isFavorite: Boolean = false,
) : Parcelable {
  val rating: Float
    get() = voteAverage.convertToFiveStarScale()
  val movieInfo: String
    get() = "${this.releaseDate.toYear()}\t\t${this.originalLanguage.toLanguageName()}\t\t${this.voteCount} ratings"
}
