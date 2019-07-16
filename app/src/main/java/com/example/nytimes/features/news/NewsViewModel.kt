package com.example.nytimes.features.news

import androidx.lifecycle.ViewModel
import com.example.nytimes.App
import androidx.lifecycle.MutableLiveData
import com.example.nytimes.data.models.NewsResponse
import com.example.nytimes.data.repository.NewsRepository
import javax.inject.Inject
import androidx.lifecycle.LiveData


class NewsViewModel: ViewModel() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var repository: NewsRepository


    fun getNewsRepository(): LiveData<NewsResponse> {
        return repository.getNews()
    }
}