package com.example.views_example.presentation.fragments.home

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.views_example.data.source.BeersPagingSourceMediator
import com.example.views_example.data.source.local.LocalDatabase
import com.example.views_example.data.source.remote.BeerApi

class HomeViewModel constructor(
    private val beersPagingSourceMediator: BeersPagingSourceMediator,
    private val database: LocalDatabase,
    private val api: BeerApi,
) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val beersFlow = Pager(
        config = PagingConfig(10),
        remoteMediator = BeersPagingSourceMediator(database, api),
        pagingSourceFactory = {
            database.beersDao.getBeers()
        }
    ).flow

}