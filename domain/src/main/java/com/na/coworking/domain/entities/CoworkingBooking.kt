package com.na.coworking.domain.entities

data class CoworkingBooking(
    val idObject: Int,
    val idWorkspace: Int,
    val timeStart: String,
    val timeEnd: String,
    val date: String,
    val isConfirmed: Boolean,
    val idUser: Int
)