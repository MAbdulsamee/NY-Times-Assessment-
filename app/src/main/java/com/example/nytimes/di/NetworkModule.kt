package com.example.nytimes.di

import android.app.Application
import com.example.nytimes.AppConfig
import com.example.nytimes.BuildConfig
import com.example.nytimes.data.api.APIs
import com.example.nytimes.data.api.CustomOkHttpInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpCache(application: Application): Cache? {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(application.cacheDir, cacheSize.toLong())
    }


    @Provides
    @Singleton
    fun provideGson(): Gson =
            GsonBuilder()
                    .setLenient()
                    .create()

    @Provides
    @Singleton
    fun provideOkHttpClient(application: Application): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BASIC

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(logging)
            .addInterceptor(CustomOkHttpInterceptor())
                .connectTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .connectionSpecs(listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
                .followSslRedirects(false)
                .followRedirects(false)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): APIs {
        return retrofit.create(APIs::class.java)
    }

}