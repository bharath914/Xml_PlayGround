package com.example.views_example.di

import com.example.views_example.data.repo.SongsRepo
import com.example.views_example.data.source.LocalMusicSource
import org.koin.dsl.module
import retrofit2.Retrofit
import kotlin.math.sin


val appModule = module {
    single {
        Retrofit.Builder()
    }
    single<LocalMusicSource> {
        LocalMusicSource(get())
    }
    single {
        SongsRepo(get())
    }
}