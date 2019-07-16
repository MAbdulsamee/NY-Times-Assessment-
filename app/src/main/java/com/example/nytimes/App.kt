package com.example.nytimes

import android.app.Application
import com.example.nytimes.di.AppComponent
import com.example.nytimes.di.AppModule
import com.example.nytimes.di.DaggerAppComponent
import com.example.nytimes.di.NetworkModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .build()

    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}