package com.example.views_example.data.entity.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("BeerRemoteKeys")
data class BeerRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("beerId")
    val beerId: String,
    val currentPage: Int,
    val prevKey: Int?,
    val nextKey: Int?,
    val createdAt: Long = System.currentTimeMillis(),
)
