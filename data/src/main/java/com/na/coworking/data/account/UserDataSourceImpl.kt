package com.na.coworking.data.account

import com.na.coworking.domain.entities.User
import com.na.coworking.domain.interfaces.account.UserDataSource

class UserDataSourceImpl(
    // TODO: service: Service
) : UserDataSource {
    override suspend fun getUser(): User {
        return User(1, "Egor", "Flashkaa", "flocktop@ya.ru", 0)
    }
}