package com.na.coworking.domain.entities

data class User(
    val id: Int,
    val firstName: String,
    val secondName: String,
    val email: String,
    val login: String,
    val accessLevel: Int
)