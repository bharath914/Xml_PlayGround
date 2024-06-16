package com.example.views_example.di

import androidx.room.Room
import com.example.views_example.data.repo.SongsRepo
import com.example.views_example.data.repo.TaxReportsRepository
import com.example.views_example.data.source.BeersPagingSourceMediator
import com.example.views_example.data.source.LocalMusicSource
import com.example.views_example.data.source.SongsPagingSource
import com.example.views_example.data.source.local.LocalDatabase
import com.example.views_example.data.source.remote.BeerApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://api.openbrewerydb.org/v1/"
val appModule = module {
    single<BeerApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BeerApi::class.java)
    }
    single<LocalDatabase> {
        Room.databaseBuilder(
            androidContext(),
            LocalDatabase::class.java,
            "local_database"
        ).addMigrations()
            .build()
    }
    single<LocalMusicSource> {
        LocalMusicSource(get())
    }
    single<SongsPagingSource> {
        SongsPagingSource(get())
    }
    single {
        SongsRepo(get())
    }

    single<BeersPagingSourceMediator> {
        BeersPagingSourceMediator(get(), get())
    }

    single {
        TaxReportsRepository()
    }
}