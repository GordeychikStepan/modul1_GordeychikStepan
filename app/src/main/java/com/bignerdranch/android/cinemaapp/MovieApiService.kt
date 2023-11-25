package com.bignerdranch.android.cinemaapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MovieApiService {

    @GET("films/premieres")
    fun getPremieres(
        @Query("year") year: Int,
        @Query("month") month: String,
        @Header("X-API-KEY") apiKey: String
    ): Call<PremieresResponse>
}