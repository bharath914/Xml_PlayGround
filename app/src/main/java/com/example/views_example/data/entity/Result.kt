package com.example.views_example.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Result(
    @SerialName("author") val author: String,
    @SerialName("authorSlug") val authorSlug: String,
    @SerialName("content") val content: String,
    @SerialName("dateAdded") val dateAdded: String,
    @SerialName("dateModified") val dateModified: String,
    @SerialName("_id") val id: String,
    @SerialName("length") val length: Int,
    @SerialName("tags") val tags: List<String>
)