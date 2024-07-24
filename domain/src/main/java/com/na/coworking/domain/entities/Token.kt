package com.na.coworking.domain.entities

data class Token(
    val value: State = State.NoValue
) {
    sealed class State(val login: String?) {
        data class HasLogin(val value: String) : State(value)
        data object NoValue : State(null)
    }
}
