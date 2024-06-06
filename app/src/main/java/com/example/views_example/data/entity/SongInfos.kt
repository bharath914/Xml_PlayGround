package com.example.views_example.data.entity

data class SongInfos(
    val mediaId: String = "1",
    val title: String = "",
    val albumName: String = "",
    val artist: String = "",
    val songUrl: String = "",
    val imageUrl: String = "",
    val duration: Long = 0L,
    val bitrate: String = "",
    val size: String = "",
    val mimeType: String = "",

    val albumartist: String = "",
    val dateAdded: String = "",
    val dateModified: String = "",
)