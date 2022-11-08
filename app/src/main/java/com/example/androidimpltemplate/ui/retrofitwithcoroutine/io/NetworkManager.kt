package com.example.androidimpltemplate.ui.retrofitwithcoroutine.io

import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import com.example.androidimpltemplate.ui.retrofitwithcoroutine.model.Country
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object NetworkManager {

    private const val BASE_URL = "https://raw.githubusercontent.com/"

    private val lock = Any()

    @Volatile var retrofitInstance : Retrofit? = null

    private fun createRetrofitInstance() = retrofitInstance ?: synchronized(lock) {
        retrofitInstance ?: Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val countryApiService: CountryServices by lazy {
        (retrofitInstance ?: createRetrofitInstance()).create(CountryServices::class.java)
    }

    suspend fun getCountyList(): Response<List<Country>> {
        return countryApiService.getCountries()
    }

}