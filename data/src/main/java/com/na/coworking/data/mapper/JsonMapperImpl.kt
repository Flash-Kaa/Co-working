package com.na.coworking.data.mapper

import com.google.gson.Gson
import com.na.coworking.domain.entities.User
import com.na.coworking.domain.interfaces.JsonMapper

internal class JsonMapperImpl : JsonMapper {
    private val gson = Gson()

    override fun <T> deserialize(json: String, clazz: Class<T>): T {
        return gson.fromJson(json, clazz)
    }

    override fun getUser(json: String): User {
        return gson.fromJson(json, UserFromToken::class.java).toUser()
    }
}