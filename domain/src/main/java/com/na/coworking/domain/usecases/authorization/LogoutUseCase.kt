package com.na.coworking.domain.usecases.authorization

import com.na.coworking.domain.entities.Token
import com.na.coworking.domain.interfaces.authorization.TokenRepository
import com.na.coworking.domain.usecases.runWithSupervisorInBackground

class LogoutUseCase(
    private val repository: TokenRepository
) {
    suspend operator fun invoke() {
        runWithSupervisorInBackground {
            repository.updateToken(Token(Token.State.NoValue))
        }
    }
}