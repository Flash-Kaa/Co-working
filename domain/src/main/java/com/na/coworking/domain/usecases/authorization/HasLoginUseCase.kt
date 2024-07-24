package com.na.coworking.domain.usecases.authorization

import com.na.coworking.domain.entities.LoadState
import com.na.coworking.domain.entities.Token
import com.na.coworking.domain.interfaces.authorization.TokenRepository
import com.na.coworking.domain.usecases.runWithSupervisorInBackground
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HasLoginUseCase(
    private val repository: TokenRepository
) {
    private val _state: MutableStateFlow<LoadState> = MutableStateFlow(LoadState.None)
    val state: StateFlow<LoadState> = _state.asStateFlow()

    suspend operator fun invoke() {
        _state.update { LoadState.Progress }

        runWithSupervisorInBackground {
            if (repository.getToken().value is Token.State.HasLogin) {
                _state.update { LoadState.Successful }
            } else {
                _state.update { LoadState.Error }
            }
        }
    }
}