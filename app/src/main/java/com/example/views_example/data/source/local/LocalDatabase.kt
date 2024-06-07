package com.example.views_example.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.views_example.data.entity.local.BeerRemoteKeys
import com.example.views_example.data.entity.local.BeersEntity

@Database(
    entities = [
        BeersEntity::class,
        BeerRemoteKeys::class
    ],
    version = 1,
)
abstract class LocalDatabase : RoomDatabase() {
    abstract val beersDao: BeersDao
    abstract val beerRemoteKeysDao: BeerKeysDao
}