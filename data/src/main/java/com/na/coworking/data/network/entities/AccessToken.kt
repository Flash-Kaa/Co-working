package com.na.coworking.data.network.entities

import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("tokenType") val tokenType: String,
    @SerializedName("accessToken") val accessToken: String
)