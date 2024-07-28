package com.na.coworking.domain.usecases.account

import com.na.coworking.domain.entities.User
import com.na.coworking.domain.interfaces.JsonMapper
import com.na.coworking.domain.interfaces.authorization.TokenRepository
import java.util.Base64

class GetUserUseCase(
    private val tokenRepository: TokenRepository,
    private val mapper: JsonMapper
) {
    suspend operator fun invoke(): User {
        return tokenRepository.getToken().value.login?.let {
            val parts = it.split(".")
            val charset = charset("UTF-8")
            val json = String(Base64.getUrlDecoder().decode(parts[1].toByteArray(charset)), charset)
            mapper.getUser(json)
        } ?: User(-1, "", "", "", "", -1)
    }
}