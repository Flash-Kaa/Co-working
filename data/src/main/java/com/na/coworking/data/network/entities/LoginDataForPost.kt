package com.na.coworking.data.network.entities

import com.google.gson.annotations.SerializedName

data class LoginDataForPost(
    @SerializedName("login") val login: String,
    @SerializedName("password") val password: String,
    @SerializedName("grantType") val grantType: String = "password",
    @SerializedName("code") val code: Int = 0
)