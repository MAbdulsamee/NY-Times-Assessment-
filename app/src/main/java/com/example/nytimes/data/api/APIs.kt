package com.example.nytimes.data.api

import com.example.nytimes.data.models.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIs {

    @GET("viewed/{period}.json")
    fun getNews(@Path("period") period: Int?): Call<NewsResponse>

}