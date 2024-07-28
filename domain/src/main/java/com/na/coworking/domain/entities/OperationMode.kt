package com.na.coworking.domain.entities

data class OperationMode(
    val id: Int,
    val timeStart: String,
    val timeEnd: String,
    val idWorkspace: Int,
    val weekDayNumber: Int
)
