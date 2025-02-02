package com.example.views_example

import android.app.Application
import com.example.views_example.di.appModule
import com.example.views_example.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(appModule, viewModelsModule)
        }
    }
}