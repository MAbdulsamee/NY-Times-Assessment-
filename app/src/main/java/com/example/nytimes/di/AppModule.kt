package com.example.nytimes.di

import android.app.Application
import com.example.nytimes.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private var context: App) {

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return context
    }

}