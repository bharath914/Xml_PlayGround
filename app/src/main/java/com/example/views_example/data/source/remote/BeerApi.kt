package com.example.views_example.data.source.remote

import com.example.views_example.data.entity.BeersResultDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApi {
    @GET("breweries")
    suspend fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
    ): List<BeersResultDTO>
}
