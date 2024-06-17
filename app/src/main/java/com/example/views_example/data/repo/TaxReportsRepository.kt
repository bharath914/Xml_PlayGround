package com.example.views_example.data.repo

import com.example.views_example.data.entity.ReportItem
import com.example.views_example.data.entity.Reports

class TaxReportsRepository {
    suspend fun getTaxReportsList(): List<Reports> {
        return listOf(
            Reports(
                "Taxes",
                listOf(
                    ReportItem("GSTR-1"),
                    ReportItem("GSTR-2B"),
                    ReportItem("GSTR-3B"),
                    ReportItem("Sales Summary by HSN"),
                    ReportItem("TDS Receivable"),
                    ReportItem("TDS Payable"),
                    ReportItem("TCS Receivable"),
                    ReportItem("TCS Payable")
                )
            ),
            Reports(
                "Transaction Reports",
                listOf(
                    ReportItem("Sales"),
                    ReportItem("Purchases"),
                    ReportItem("Sale Returns"),
                    ReportItem("Purchase Returns"),
                    ReportItem("Estimates"),
                    ReportItem("Invoices"),
                    ReportItem("Credit Notes"),
                    ReportItem("Debit Notes")
                )
            ),
            Reports(
                "Bill-Wise Item Reports",
                listOf(
                    ReportItem("Sales"),
                    ReportItem("Purchases"),
                    ReportItem("Estimates"),
                    ReportItem("Delivery Challans"),
                    ReportItem("Pro Forma Invoices"),
                    ReportItem("Purchase Orders")
                )
            ),
            Reports(
                "Item Reports", listOf(
                    ReportItem("Stock Summary"),
                    ReportItem("Item-wise discount"),
                    ReportItem("Stock Value"),
                    ReportItem("Inventory Timeline"),
                )
            )
        )
    }
}