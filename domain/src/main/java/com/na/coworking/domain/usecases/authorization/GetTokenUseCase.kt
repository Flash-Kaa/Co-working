package com.na.coworking.domain.usecases.authorization

import com.na.coworking.domain.entities.Token
import com.na.coworking.domain.interfaces.authorization.TokenRepository

class GetTokenUseCase(
    private val repository: TokenRepository
) {
    suspend operator fun invoke(): Token {
        return repository.getToken()
    }
}