package com.na.coworking.domain.interfaces.authorization

import com.na.coworking.domain.entities.AuthorizationData
import com.na.coworking.domain.entities.Token

interface AuthorizationDataSource {
    suspend fun authorize(data: AuthorizationData): Token
}