package com.na.coworking.domain.interfaces.authorization

import com.na.coworking.domain.entities.Token
import kotlinx.coroutines.flow.Flow

interface TokenDataSource {
    suspend fun getToken(): Token

    suspend fun updateToken(token: Token)
}