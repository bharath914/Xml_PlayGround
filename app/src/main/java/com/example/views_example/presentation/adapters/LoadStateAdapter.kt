package com.example.views_example.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.views_example.R

class LoadViewHolder(view: View) : ViewHolder(view) {

}

class BeerLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoadViewHolder>() {


    override fun onBindViewHolder(holder: LoadViewHolder, loadState: LoadState) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadViewHolder {
        return LoadViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.load_state_item, parent, false)
        )
    }
}