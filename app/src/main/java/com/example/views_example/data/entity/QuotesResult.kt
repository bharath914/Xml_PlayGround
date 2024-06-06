package com.example.views_example.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class QuotesResult(
    @SerialName("count") val count: Int,
    @SerialName("lastItemIndex") val lastItemIndex: Int,
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<Result>,
    @SerialName("totalCount") val totalCount: Int,
    @SerialName("totalPages") val totalPages: Int
)