package com.na.coworking.data.mapper

import com.google.gson.annotations.SerializedName
import com.na.coworking.domain.entities.User

data class UserFromToken(
    @SerializedName("Id") val id: Int,
    @SerializedName("FirstName") val firstName: String,
    @SerializedName("LastName") val secondName: String,
    @SerializedName("Email") val email: String,
    @SerializedName("Login") val login: String,
    @SerializedName("AccessLevel") val accessLevel: Int,
    @SerializedName("role") val role: String,
    @SerializedName("hbf") val hbf: Int,
    @SerializedName("exp") val exp: Int,
    @SerializedName("iat") val iat: Int
) {
    fun toUser(): User = User(
        id = id,
        login = login,
        email = email,
        accessLevel = accessLevel,
        firstName = firstName,
        secondName = secondName
    )
}