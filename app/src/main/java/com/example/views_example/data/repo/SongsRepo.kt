package com.example.views_example.data.repo

import com.example.views_example.data.entity.SongInfos
import com.example.views_example.data.source.LocalMusicSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SongsRepo(private val localMusicSource: LocalMusicSource) {
    private val songList = mutableListOf<SongInfos>()

    suspend fun getSongsList(): List<SongInfos> {
        return withContext(Dispatchers.IO) {
            songList.ifEmpty {
                val newList = localMusicSource.getSongsWithMoreDetails()
                songList.addAll(newList)
                newList
            }
        }
    }
}

