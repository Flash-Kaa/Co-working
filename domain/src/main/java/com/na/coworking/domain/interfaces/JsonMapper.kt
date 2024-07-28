package com.na.coworking.domain.interfaces

import com.na.coworking.domain.entities.User

interface JsonMapper {
    fun <T> deserialize(json: String, clazz: Class<T>): T

    fun getUser(json: String): User
}