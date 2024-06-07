package com.example.views_example.data.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.views_example.data.entity.local.BeerRemoteKeys
import com.example.views_example.data.entity.local.BeersEntity
@Dao
interface BeersDao {
    @Upsert
    suspend fun upsertBeers(list: List<BeersEntity>)

    @Query("Delete from Beers")
    suspend fun clearAll()

    @Query("Select * from Beers")
    fun getBeers(): PagingSource<Int,BeersEntity>

    @Query("Select * from Beers where id = :id")
    fun getBeer(id: String): BeersEntity?


}
@Dao
interface BeerKeysDao {
    @Upsert
    suspend fun upsertBeers(list: List<BeerRemoteKeys>)

    @Query("Delete from BeerRemoteKeys")
    suspend fun clearAll()

    @Query("Select * from BeerRemoteKeys")
    fun getBeers(): List<BeerRemoteKeys>

    @Query("Select * from BeerRemoteKeys where beerId = :id")
    fun getBeer(id: String): BeerRemoteKeys?
}