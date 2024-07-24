package com.na.coworking.ui.authorization

import com.na.coworking.domain.entities.AuthorizationData

data class UserLoginStateUI(
    val login: String = "",
    val password: String = ""
) {
    fun toAuthData() = AuthorizationData(login, password)
}
