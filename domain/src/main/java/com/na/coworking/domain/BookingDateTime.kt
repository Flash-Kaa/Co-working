package com.na.coworking.domain

data class BookingDateTime(
    val date: String,
    val timeRange: String,
    val times: List<Pair<String, String>>
)
