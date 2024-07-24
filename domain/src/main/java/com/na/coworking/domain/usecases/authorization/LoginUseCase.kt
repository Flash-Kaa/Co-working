package com.na.coworking.domain.usecases.authorization

import com.na.coworking.domain.entities.AuthorizationData
import com.na.coworking.domain.entities.LoadState
import com.na.coworking.domain.usecases.runWithSupervisorInBackground
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginUseCase(
    private val updateTokenUseCase: UpdateTokenUseCase,
    private val authorizeUseCase: AuthorizeDataUseCase
) {
    private val _state: MutableStateFlow<LoadState> = MutableStateFlow(LoadState.None)
    val state: StateFlow<LoadState> = _state.asStateFlow()

    suspend operator fun invoke(loginData: AuthorizationData) {
        _state.update { LoadState.Progress }

        runWithSupervisorInBackground(
            onErrorAction = { _state.update { LoadState.Error } }
        ) {
            val token = authorizeUseCase(loginData)
            updateTokenUseCase(token)

            _state.update { LoadState.Successful }
        }
    }
}