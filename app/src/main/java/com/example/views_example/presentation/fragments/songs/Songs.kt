package com.example.views_example.presentation.fragments.songs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.views_example.databinding.FragmentSongsBinding
import com.example.views_example.presentation.adapters.SongListAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class Songs : Fragment() {

    private val viewModel: SongViewModel by viewModel()
    private lateinit var binding: FragmentSongsBinding
    private val songAdapter = SongListAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSongsBinding.inflate(inflater, container, false)
        setViews()
        collectState()
        return binding.root
    }

    private fun setViews() {
        binding.songsRecyclerView.apply {
            this.adapter = songAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun collectState() {
        lifecycleScope.launch {
            viewModel.songsPagingFlow.collectLatest {
                songAdapter.submitData(it)

            }
        }
    }


}