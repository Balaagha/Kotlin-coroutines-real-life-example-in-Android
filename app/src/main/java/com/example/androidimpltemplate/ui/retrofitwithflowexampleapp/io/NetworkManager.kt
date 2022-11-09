package com.example.androidimpltemplate.ui.retrofitwithflowexampleapp.io

import com.example.androidimpltemplate.ui.retrofitwithcoroutine.model.Country
import com.example.androidimpltemplate.ui.retrofitwithflowexampleapp.model.NewsArticle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {

    private const val BASE_URL = "https://raw.githubusercontent.com/DevTides/NewsApi /master/"
    private const val DELAY_TIME = 2000L

    private val lock = Any()

    @Volatile var retrofitInstance : Retrofit? = null

    private fun createRetrofitInstance() = retrofitInstance ?: synchronized(lock) {
        retrofitInstance ?: Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val newsApiService: NewsServices by lazy {
        (retrofitInstance ?: createRetrofitInstance()).create(NewsServices::class.java)
    }

    fun getNewsList(): Flow<NewsArticle> {
        return flow {
            with(newsApiService.getNews()){
                forEach {
                    emit(it)
                    delay(DELAY_TIME)
                }
            }
        }
    }

}