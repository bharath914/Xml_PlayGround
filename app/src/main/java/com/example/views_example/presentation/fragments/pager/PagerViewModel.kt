package com.example.views_example.presentation.fragments.pager

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PagerViewModel : ViewModel() {

    private val _imageUris = MutableStateFlow(emptyList<String>())
    val imageUris = _imageUris.asStateFlow()

    init {
        val list = listOf(
            "https://th.bing.com/th/id/R.34bdc0ab541552ee99fcc08bf5b8ace8?rik=CsTpM5iYVxV%2bMw&riu=http%3a%2f%2fgetwallpapers.com%2fwallpaper%2ffull%2fe%2f6%2f8%2f364124.jpg&ehk=pPAyRGiw998Mk%2fAGorAYz9RO4BV%2f%2flQvgV%2bQCkwhxcM%3d&risl=&pid=ImgRaw&r=0",
            "https://wallpapercave.com/wp/wp8111706.jpg",
            "https://wallpaperaccess.com/full/4778519.jpg",
            "https://th.bing.com/th/id/OIP.fAI9NUpdBRacPmQVRtVWEAHaNK?w=1080&h=1920&rs=1&pid=ImgDetMain",
            "https://th.bing.com/th/id/OIP.UtqRhvRakKwuIJ_NnigPLwHaNK?w=728&h=1294&rs=1&pid=ImgDetMain",
            "https://th.bing.com/th/id/OIP.6vVa66Ka4OzmeWmXDAnTJwHaNK?w=1440&h=2560&rs=1&pid=ImgDetMain",
        )
        _imageUris.update {
            list
        }
    }
}