package com.na.coworking.data.authorization

import com.na.coworking.domain.entities.AuthorizationData
import com.na.coworking.domain.entities.Token
import com.na.coworking.domain.interfaces.authorization.AuthorizationDataSource
import com.na.coworking.domain.interfaces.authorization.AuthorizationRepository

class AuthorizationRepoImpl(
    private val dataSource: AuthorizationDataSource
): AuthorizationRepository {
    override suspend fun authorize(data: AuthorizationData): Token {
        return dataSource.authorize(data)
    }
}