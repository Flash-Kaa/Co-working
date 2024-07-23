package com.na.coworking.data.authorization

import com.na.coworking.domain.entities.Token
import com.na.coworking.domain.interfaces.authorization.TokenDataSource
import com.na.coworking.domain.interfaces.authorization.TokenRepository

class TokenRepoImpl(
    private val dataSource: TokenDataSource
): TokenRepository {
    override suspend fun getToken(): Token {
        return dataSource.getToken()
    }

    override suspend fun updateToken(token: Token) {
        dataSource.updateToken(token)
    }
}