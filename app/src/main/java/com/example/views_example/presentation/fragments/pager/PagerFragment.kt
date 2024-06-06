package com.example.views_example.presentation.fragments.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import com.example.views_example.databinding.FragmentSettingsBinding
import com.example.views_example.presentation.adapters.ImageViewPagerAdapter
import com.example.views_example.presentation.util.ZoomPagerTransformation
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PagerFragment : Fragment() {

    private val viewModel by viewModel<PagerViewModel>()
    private lateinit var binding: FragmentSettingsBinding
    private val imageAdapter = ImageViewPagerAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        setUpViewPager()
        collectState()

        return binding.root
    }

    private fun collectState() {
        viewModel.apply {
            viewModelScope.launch {
                this@apply.imageUris.collectLatest {
                    imageAdapter.submitList(it)
                }
            }
        }
    }


    private fun setUpViewPager() {
        binding.imagesViewPager.apply {
            adapter = imageAdapter
            setPageTransformer(ZoomPagerTransformation())
        }
    }



}