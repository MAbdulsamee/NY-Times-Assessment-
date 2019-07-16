package com.example.nytimes.data.api

import com.example.nytimes.AppConfig
import okhttp3.Interceptor
import okhttp3.Response

class CustomOkHttpInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api-key", AppConfig.API_KEY)
            .build()

        val request = originalRequest.newBuilder().url(url).build()
        return chain.proceed(request)

    }
}