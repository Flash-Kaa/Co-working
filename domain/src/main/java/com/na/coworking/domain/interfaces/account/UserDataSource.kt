package com.na.coworking.domain.interfaces.account

import com.na.coworking.domain.entities.User

interface UserDataSource {
    suspend fun getUser(): User
}