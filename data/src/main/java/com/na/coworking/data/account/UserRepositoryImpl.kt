package com.na.coworking.data.account

import com.na.coworking.domain.entities.User
import com.na.coworking.domain.interfaces.account.UserDataSource
import com.na.coworking.domain.interfaces.account.UserRepository

class UserRepositoryImpl(
    private val dataSource: UserDataSource
) : UserRepository {
    override suspend fun getUser(): User {
        return dataSource.getUser()
    }
}