package com.example.views_example.presentation.fragments.songs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.views_example.data.entity.SongInfos
import com.example.views_example.data.repo.SongsRepo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SongViewModel constructor(
    private val songsRepo: SongsRepo,
) : ViewModel() {
    private val _songsList = MutableStateFlow(emptyList<SongInfos>())
    val songsList = _songsList.asStateFlow()

    init {
        viewModelScope.launch(IO) {
            _songsList.update {
                songsRepo.getSongsList()
            }
        }
    }


}