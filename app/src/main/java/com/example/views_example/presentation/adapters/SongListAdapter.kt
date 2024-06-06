package com.example.views_example.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.views_example.R
import com.example.views_example.data.entity.SongInfos
import com.google.android.material.card.MaterialCardView

class SongListAdapter : ListAdapter<SongInfos, SongListAdapter.SongViewHolder>(SongDiffUtil()) {
    inner class SongViewHolder(view: View) : ViewHolder(view) {

        private val cardView: MaterialCardView = view.findViewById(R.id.cv_songCard)
        private val songName: TextView = view.findViewById(R.id.tv_songName)
        private val songImage: ImageView = view.findViewById(R.id.iv_songImage)
        val glide = Glide.with(view)
        fun bind(songInfo: SongInfos) {
            songName.text = songInfo.title
            glide
                .load(songInfo.imageUrl)
                .override(512)
                .into(songImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.song_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

class SongDiffUtil : DiffUtil.ItemCallback<SongInfos>() {
    override fun areItemsTheSame(oldItem: SongInfos, newItem: SongInfos): Boolean {
        return oldItem.mediaId == newItem.mediaId
    }

    override fun areContentsTheSame(oldItem: SongInfos, newItem: SongInfos): Boolean {
        return oldItem == newItem
    }
}