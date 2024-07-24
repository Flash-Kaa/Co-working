package com.na.coworking.actions

sealed class AuthorizationAction {
    // Navigation
    data object ToRegistration : AuthorizationAction()
}

sealed class AuthorizationEvent(
    open val onError: () -> Unit,
    open val onSuccess: () -> Unit,
    open val onProgress: () -> Unit
) {
    data class Authorization(
        override val onError: () -> Unit = { },
        override val onSuccess: () -> Unit = { },
        override val onProgress: () -> Unit = { }
    ) : AuthorizationEvent(onError, onSuccess, onProgress)
}