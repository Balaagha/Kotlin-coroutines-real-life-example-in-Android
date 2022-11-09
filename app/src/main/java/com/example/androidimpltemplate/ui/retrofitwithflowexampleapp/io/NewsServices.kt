package com.example.androidimpltemplate.ui.retrofitwithflowexampleapp.io

import com.example.androidimpltemplate.ui.retrofitwithflowexampleapp.model.NewsArticle
import retrofit2.http.GET

interface NewsServices {

    @GET("news.json")
    suspend fun getNews(): List<NewsArticle>
}