package com.na.coworking.data.authorization

import com.na.coworking.domain.entities.AuthorizationData
import com.na.coworking.domain.entities.Token
import com.na.coworking.domain.interfaces.authorization.AuthorizationDataSource
import kotlinx.coroutines.delay

internal class AuthorizationDSImpl(
    // TODO: private val service: AuthorizationService
) : AuthorizationDataSource {
    override suspend fun authorize(data: AuthorizationData): Token {
        delay(1000L)
        if (data.login != "login" || data.password != "password") {
            throw IllegalArgumentException("unexpected auth with hard data")
        }

        return Token(Token.State.HasLogin(data.login + data.password))
    }
}