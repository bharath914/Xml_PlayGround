package com.example.views_example.data.extensions

import com.example.views_example.data.entity.BeersResultDTO
import com.example.views_example.data.entity.local.BeersEntity


fun BeersResultDTO.toEntity() = BeersEntity(
    address1 = address1 ?: "N/A",
    address2 = address2 ?: "N/A",
    address3 = address3 ?: "N/A",
    breweryType = breweryType ?: "",
    city = city ?: "",
    country = country ?:"N/A",
    id = id ?: "N/A",
    latitude = latitude ?: "N/A",
    longitude = longitude ?: "N/A",
    name = name ?: "N/A",
    phone = phone ?: "N/A",
    postalCode = postalCode?:"N/A",
    state = state ?:"N/A",
    stateProvince = stateProvince ?:"N/A",
    street = street?:"N/A",
    websiteUrl = websiteUrl ?: "N/A"
)