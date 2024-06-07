package com.example.views_example.data.entity


import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class BeersResultDTO(
    @SerialName("address_1") val address1: String?,
    @SerialName("address_2") val address2: String?,
    @SerialName("address_3") val address3: String?,
    @SerialName("brewery_type") val breweryType: String?,
    @SerialName("city") val city: String?,
    @SerialName("country") val country: String?,
    @SerialName("id") val id: String?,
    @SerialName("latitude") val latitude: String?,
    @SerialName("longitude") val longitude: String?,
    @SerialName("name") val name: String?,
    @SerialName("phone") val phone: String?,
    @SerialName("postal_code") val postalCode: String?,
    @SerialName("state") val state: String?,
    @SerialName("state_province") val stateProvince: String?,
    @SerialName("street") val street: String?,
    @SerialName("website_url") val websiteUrl: String?,
)