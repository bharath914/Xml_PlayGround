package com.example.views_example.presentation.fragments.expandableCards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.views_example.data.repo.TaxReportsRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReportsViewModel(private val taxReportsRepository: TaxReportsRepository) : ViewModel() {
    private val _taxReports = MutableStateFlow(emptyList<ExpandableListItem>())
    val taxReports = _taxReports.asStateFlow()

    fun getList() {
        viewModelScope.launch(IO) {
            val list = withContext(IO) {
                taxReportsRepository.getTaxReportsList()
            }
            _taxReports.update {
                list.map { it.toExpandableListItem() }
            }
        }
    }
}