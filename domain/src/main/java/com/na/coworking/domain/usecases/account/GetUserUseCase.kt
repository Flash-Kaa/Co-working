package com.na.coworking.domain.usecases.account

import com.na.coworking.domain.entities.User
import com.na.coworking.domain.interfaces.account.UserRepository

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): User {
        return userRepository.getUser()
    }
}