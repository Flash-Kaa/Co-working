package com.na.coworking.domain.interfaces.authorization

import com.na.coworking.domain.entities.AuthorizationData
import com.na.coworking.domain.entities.Token
import kotlinx.coroutines.flow.Flow

interface AuthorizationDataSource {
    suspend fun authorize(data: AuthorizationData): Token
}