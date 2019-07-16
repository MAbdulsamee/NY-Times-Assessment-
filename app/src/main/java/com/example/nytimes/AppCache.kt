package com.example.nytimes

import androidx.lifecycle.MutableLiveData
import com.example.nytimes.data.models.NewsResponse

object AppCache {

    val newsData = MutableLiveData<NewsResponse>()
}