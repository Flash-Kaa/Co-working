package com.na.coworking.domain.entities

data class Booking(
    val id: Int,
    val name: String,
    val timeStart: String,
    val timeEnd: String,
    val date: String,
    val isConfirmed: Boolean,
    val image: String
)
