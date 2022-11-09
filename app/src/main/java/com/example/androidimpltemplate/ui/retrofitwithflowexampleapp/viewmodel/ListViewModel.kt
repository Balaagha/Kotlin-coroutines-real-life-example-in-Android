package com.example.androidimpltemplate.ui.retrofitwithflowexampleapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.androidimpltemplate.ui.retrofitwithflowexampleapp.io.NetworkManager

class ListViewModel: ViewModel() {


    val newsArticles = NetworkManager.getNewsList().asLiveData()

    override fun onCleared() {
        super.onCleared()
    }
}