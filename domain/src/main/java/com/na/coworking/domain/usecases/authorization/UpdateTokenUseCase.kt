package com.na.coworking.domain.usecases.authorization

import com.na.coworking.domain.entities.Token
import com.na.coworking.domain.interfaces.authorization.TokenRepository

class UpdateTokenUseCase(
    private val repository: TokenRepository
) {
    suspend operator fun invoke(token: Token) {
        repository.updateToken(token)
    }
}