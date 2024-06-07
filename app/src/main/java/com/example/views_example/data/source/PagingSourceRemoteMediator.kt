package com.example.views_example.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.views_example.data.entity.SongInfos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SongsPagingSource(
    private val localMusicSource: LocalMusicSource,
) : PagingSource<Int, SongInfos>() {
    override fun getRefreshKey(state: PagingState<Int, SongInfos>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SongInfos> {
        return withContext(Dispatchers.IO) {

            try {
                val page = params.key ?: 0
                val startIndex = (page) * 20
                val endIndex = startIndex + 20
                val data = localMusicSource.getSongsWithMoreDetails(startIndex, endIndex)
                LoadResult.Page(
                    data = data,
                    prevKey = if (startIndex == 0) null else page - 1,
                    nextKey = if (data.isEmpty()) null else page + 1
                )
            } catch (e: Exception) {
                e.printStackTrace()
                LoadResult.Error(e)
            }
        }
    }
}