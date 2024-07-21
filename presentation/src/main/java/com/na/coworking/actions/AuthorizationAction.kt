package com.na.coworking.actions

import com.na.coworking.ui.authorization.UserLoginStateUI

sealed class AuthorizationAction {
    // Navigation
    data object ToRegistration: AuthorizationAction()

    data class Authorization(val userData: UserLoginStateUI): AuthorizationAction()
}