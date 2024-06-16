package com.example.views_example.presentation.fragments.expandableCards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.views_example.databinding.FragmentsReportsBinding
import com.example.views_example.presentation.adapters.ReportsAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReportsScreen : Fragment() {

    private lateinit var binding: FragmentsReportsBinding
    private val viewModel: ReportsViewModel by viewModel()
    private val reportsAdapter = ReportsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentsReportsBinding.inflate(inflater, container, false)
        initList()
        return binding.root
    }

    private fun initList() {
        viewModel.getList()
        binding.apply {
            rvReports.apply {
                adapter = reportsAdapter
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
            }
        }
        lifecycleScope.launch {
            viewModel.taxReports.collectLatest {
                reportsAdapter.submitList(it)
            }
        }
    }


}