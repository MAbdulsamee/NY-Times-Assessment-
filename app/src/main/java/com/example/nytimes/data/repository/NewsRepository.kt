package com.example.nytimes.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.nytimes.AppCache
import com.example.nytimes.data.api.APIs
import com.example.nytimes.data.models.NewsResponse
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Singleton
class NewsRepository @Inject constructor(private val api: APIs) {

    fun getNews(): MutableLiveData<NewsResponse> {

        if (AppCache.newsData.value == null) {

            api.getNews(7).enqueue(object : Callback<NewsResponse> {
                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    if (response.isSuccessful) {
                        AppCache.newsData.value = response.body()
                    }
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    AppCache.newsData.value = null
                }
            })

        }

        return AppCache.newsData
    }
}