package com.example.views_example.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.views_example.R
import com.example.views_example.data.entity.local.BeersEntity
import com.google.android.material.textview.MaterialTextView

class BeersAdapter : PagingDataAdapter<BeersEntity, RecyclerView.ViewHolder>(BeerDiffUtil()) {
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val beerName: MaterialTextView = itemView.findViewById(R.id.tv_beerTitle)
        val beerType: MaterialTextView = itemView.findViewById(R.id.tv_beerType)
        val beerLocation: MaterialTextView = itemView.findViewById(R.id.tv_beerLocation)
        fun bind(beersEntity: BeersEntity) {
            beersEntity?.apply {
                beerName.text = name
                beerType.text = breweryType
                beerLocation.text = state
            }
        }
    }

    companion object {
        private const val ITEM_VIEW_TYPE_DATA = 0
        private const val ITEM_VIEW_TYPE_LOADING = 1
    }
    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount) ITEM_VIEW_TYPE_LOADING else ITEM_VIEW_TYPE_DATA
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MyViewHolder) {
            holder.apply {
                getItem(position)?.let { entity ->
                    bind(entity)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_LOADING -> {
                LoadViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.load_state_item, parent, false)
                )
            }

            else -> {
                MyViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.beers_list_item, parent, false)
                )
            }
        }
    }
}

class BeerDiffUtil : DiffUtil.ItemCallback<BeersEntity>() {
    override fun areItemsTheSame(oldItem: BeersEntity, newItem: BeersEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BeersEntity, newItem: BeersEntity): Boolean {
        return oldItem == newItem
    }
}