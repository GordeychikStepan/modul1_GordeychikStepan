package com.bignerdranch.android.cinemaapp

data class PremieresResponse(
    val total: Int,
    val items: List<Movie>
)