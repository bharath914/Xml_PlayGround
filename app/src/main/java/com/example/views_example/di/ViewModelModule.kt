package com.example.views_example.di

import com.example.views_example.presentation.fragments.home.HomeViewModel
import com.example.views_example.presentation.fragments.pager.PagerViewModel
import com.example.views_example.presentation.fragments.songs.SongViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {
    viewModelOf(::PagerViewModel)
    viewModelOf(::SongViewModel)
    viewModelOf(::HomeViewModel)
}