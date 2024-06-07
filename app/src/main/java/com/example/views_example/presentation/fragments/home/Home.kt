package com.example.views_example.presentation.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.views_example.databinding.FragmentHomeBinding
import com.example.views_example.presentation.adapters.BeerLoadStateAdapter
import com.example.views_example.presentation.adapters.BeersAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class Home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()
    private val beerAdapter = BeersAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setObservers()
        loadState()

        return binding.root
    }

    private fun setObservers() {
        binding.rvBeersRecycler.apply {
            adapter = beerAdapter.withLoadStateFooter(
                BeerLoadStateAdapter {
                    beerAdapter.retry()
                }
            )
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        lifecycleScope.launch {
            viewModel.beersFlow
                .collectLatest { data ->
                    beerAdapter.submitData(data)
                }
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            lifecycleScope.launch {
                delay(2000)
                binding.shimmerLayout.apply {
                    visibility = View.VISIBLE
                    startShimmer()
                }
                beerAdapter.refresh()

            }.invokeOnCompletion {
                binding.shimmerLayout.apply {
                    stopShimmer()
                    visibility = View.GONE
                }
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun loadState() {
        beerAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                binding.shimmerLayout.apply {
                    if (beerAdapter.itemCount == 0) {
                        startShimmer()
                        visibility = View.VISIBLE
                    }else{
                        stopShimmer()
                        visibility = View.GONE
                    }
                }
            }


            lifecycleScope.launch {
                if (loadState.source.refresh is LoadState.NotLoading) {
                    binding.shimmerLayout.apply {
                        stopShimmer()
                        visibility = View.GONE
                    }
                } else
                    binding.shimmerLayout.startShimmer()
            }
        }


    }


}