package com.example.androidimpltemplate.ui.retrofitwithcoroutine.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidimpltemplate.ui.retrofitwithcoroutine.io.NetworkManager
import com.example.androidimpltemplate.ui.retrofitwithcoroutine.model.Country
import kotlinx.coroutines.*

class ListViewModel: ViewModel() {
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler{ coroutineContext, throwable ->
        onError("Exception is ${throwable.localizedMessage}")
    }

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = NetworkManager.countryApiService.getCountries()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    countries.value = response.body()
                    loading.value = false
                } else {
                    onError("Error: ${response.message()}")
                }
            }
        }
    }

    private fun onError(message: String) {
        countryLoadError.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}