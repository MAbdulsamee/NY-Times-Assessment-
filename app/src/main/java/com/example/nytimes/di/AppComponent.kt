package com.example.nytimes.di

import com.example.nytimes.features.news.NewsViewModel
import dagger.Component
import javax.inject.Singleton


@Component(modules = [(AppModule::class), (NetworkModule::class)])
@Singleton
interface AppComponent {

    fun inject(viewModel: NewsViewModel)


}