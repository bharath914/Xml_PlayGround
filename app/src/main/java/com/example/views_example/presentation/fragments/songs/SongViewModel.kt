package com.example.views_example.presentation.fragments.songs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.views_example.data.entity.SongInfos
import com.example.views_example.data.repo.SongsRepo
import com.example.views_example.data.source.SongsPagingSource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SongViewModel constructor(
    private val songsRepo: SongsRepo,
    private val pagingSource: SongsPagingSource
) : ViewModel() {
    private val _songsList = MutableStateFlow(emptyList<SongInfos>())
    val songsList = _songsList.asStateFlow()


    val songsPagingFlow = Pager(
        config = PagingConfig(20),
        initialKey = null,
        pagingSourceFactory = {
            pagingSource
        }
    ).flow
    init {
        viewModelScope.launch(IO) {
            _songsList.update {
                songsRepo.getSongsList()
            }
        }
    }


}