package com.na.coworking.ui.account

import com.na.coworking.domain.entities.User

data class UserStateUI(
    val id: Int = -1,
    val firstName: String = "",
    val secondName: String = "",
    val email: String = "",
    val accessLevel: Int = -1
) {
    companion object {
        fun User.toStateUI() = UserStateUI(
            id = id,
            firstName = firstName,
            secondName = secondName,
            email = email,
            accessLevel = accessLevel
        )
    }
}
