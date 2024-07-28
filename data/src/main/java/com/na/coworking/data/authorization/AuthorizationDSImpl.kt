package com.na.coworking.data.authorization

import com.na.coworking.data.network.ApiService
import com.na.coworking.data.network.entities.LoginDataForPost
import com.na.coworking.domain.entities.AuthorizationData
import com.na.coworking.domain.entities.Token
import com.na.coworking.domain.interfaces.authorization.AuthorizationDataSource

internal class AuthorizationDSImpl(
    private val service: ApiService
) : AuthorizationDataSource {
    /**
     * Need coroutine exception handler
     */
    override suspend fun authorize(data: AuthorizationData): Token {
        val response = service.authorize(
            LoginDataForPost(
                login = data.login,
                password = data.password
            )
        ).execute()

        // or throw exception that will be intercepted by the coroutine exception handler
        return Token(Token.State.HasLogin(response.body()!!.accessToken))
    }
}