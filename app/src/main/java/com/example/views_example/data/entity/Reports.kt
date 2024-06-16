package com.example.views_example.data.entity

data class Reports(
    val reportName: String = "",
    val reportItems: List<ReportItem> = emptyList(),
)

data class ReportItem(
    val itemName: String = "",
)
