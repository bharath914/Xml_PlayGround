package com.example.views_example.presentation.fragments.expandableCards

import com.example.views_example.data.entity.Reports

data class ExpandableListItem(
    val title: String = "",
    var isExpanded: Boolean = false,
    val items: List<OnExpandedItems> = emptyList(),
)

data class OnExpandedItems(
    val title: String,
)


fun Reports.toExpandableListItem(): ExpandableListItem {
    return ExpandableListItem(
        title = this.reportName,
        items = this.reportItems.map { OnExpandedItems(it.itemName) }
    )
}