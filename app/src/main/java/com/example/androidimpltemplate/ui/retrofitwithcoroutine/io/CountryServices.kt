package com.example.androidimpltemplate.ui.retrofitwithcoroutine.io

import com.example.androidimpltemplate.ui.retrofitwithcoroutine.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountryServices {

    @GET("DevTides/countries/master/countriesV2.json")
    suspend fun getCountries(): Response<List<Country>>
}