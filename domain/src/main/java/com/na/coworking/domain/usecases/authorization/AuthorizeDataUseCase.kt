package com.na.coworking.domain.usecases.authorization

import com.na.coworking.domain.entities.AuthorizationData
import com.na.coworking.domain.entities.Token
import com.na.coworking.domain.interfaces.authorization.AuthorizationRepository

class AuthorizeDataUseCase(
    private val repository: AuthorizationRepository
) {
    suspend operator fun invoke(data: AuthorizationData): Token {
        return repository.authorize(data)
    }
}