package com.example.views_example.data.source

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.views_example.data.entity.local.BeerRemoteKeys
import com.example.views_example.data.entity.local.BeersEntity
import com.example.views_example.data.extensions.toEntity
import com.example.views_example.data.source.local.LocalDatabase
import com.example.views_example.data.source.remote.BeerApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPagingApi::class)
class BeersPagingSourceMediator(
    private val database: LocalDatabase,
    private val apiService: BeerApi,
) :
    RemoteMediator<Int, BeersEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeersEntity>,
    ): MediatorResult {
        try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentItem(state)
                    remoteKeys?.nextKey?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevKey = remoteKeys?.prevKey
                    prevKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.nextKey
                    nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }


            }
            Log.d("Beers", "load: $currentPage and ${state.config.pageSize} ")
            val result = apiService.getBeers(currentPage, state.config.pageSize)
            val endOfPaginationReached = result.isEmpty()
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.beerRemoteKeysDao.clearAll()
                    database.beersDao.clearAll()
                }
                val prevKey = if (currentPage == 1) null else currentPage - 1
                val nextKey = if (endOfPaginationReached) null else currentPage + 1
                val remoteKeys = result.map {
                    BeerRemoteKeys(
                        beerId = it.id ?: "",
                        prevKey = prevKey,
                        nextKey = nextKey,
                        currentPage = currentPage
                    )
                }
                withContext(Dispatchers.IO) {
                    database.beersDao.upsertBeers(result.map { it.toEntity() })
                    database.beerRemoteKeysDao.upsertBeers(remoteKeys)
                }
            }
            return MediatorResult.Success(endOfPaginationReached)

        } catch (e: Exception) {
            e.printStackTrace()
            return MediatorResult.Error(e)
        }

    }

    private suspend fun getRemoteKeyClosestToCurrentItem(state: PagingState<Int, BeersEntity>): BeerRemoteKeys? {
        return withContext(Dispatchers.IO) {

            state.anchorPosition?.let { position ->
                state.closestItemToPosition(anchorPosition = position)?.id?.let { beerId ->
                    database.beerRemoteKeysDao.getBeer(beerId)
                }
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, BeersEntity>): BeerRemoteKeys? {
        return withContext(Dispatchers.IO) {
            state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
                ?.let { beersEntity ->
                    database.beerRemoteKeysDao.getBeer(beersEntity.id)
                }
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, BeersEntity>): BeerRemoteKeys? {
        return withContext(Dispatchers.IO) {

            state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
                ?.let { beersEntity ->
                    database.beerRemoteKeysDao.getBeer(beersEntity.id)
                }
        }
    }


}