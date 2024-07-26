package com.na.coworking.domain.interfaces.account

import com.na.coworking.domain.entities.User

interface UserRepository {
    suspend fun getUser(): User
}