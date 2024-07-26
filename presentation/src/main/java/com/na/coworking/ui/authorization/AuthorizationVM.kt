package com.na.coworking.ui.authorization

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.na.coworking.actions.AuthorizationAction
import com.na.coworking.actions.AuthorizationEvent
import com.na.coworking.domain.entities.LoadState
import com.na.coworking.domain.usecases.authorization.HasLoginUseCase
import com.na.coworking.domain.usecases.authorization.LoginUseCase
import com.na.coworking.navigation.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class AuthorizationVM(
    private val router: Router,
    private val loginUseCase: LoginUseCase,
    private val hasLoginUseCase: HasLoginUseCase
) : ViewModel() {
    val state = mutableStateOf(UserLoginStateUI())

    init {
        checkHasLogin()
    }

    private fun checkHasLogin() {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                hasLoginUseCase()
            }

            launch {
                collectHasLoginState()
            }
        }
    }

    private suspend fun collectHasLoginState() {
        hasLoginUseCase.state.collectLatest { newState ->
            if (newState == LoadState.Successful) {
                withContext(Dispatchers.Main) {
                    router.navigateToMainPageWithSetStartDestination()
                }
            }
        }
    }

    fun getAction(action: AuthorizationAction): () -> Unit {
        return when (action) {
            AuthorizationAction.ToRegistration -> ::toRegister
        }
    }

    fun getEvent(event: AuthorizationEvent) {
        when (event) {
            is AuthorizationEvent.Authorization -> login(event)
        }
    }

    private fun toRegister() {}

    private fun login(event: AuthorizationEvent.Authorization) {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                loginUseCase(state.value.toAuthData())
            }

            launch {
                executeLoginFromFlow(loginUseCase.state, event)
            }
        }
    }

    private suspend fun executeLoginFromFlow(flow: Flow<LoadState>, event: AuthorizationEvent) {
        flow.collectLatest {
            withContext(Dispatchers.Main) {
                when (it) {
                    LoadState.Successful -> {
                        event.onSuccess()
                        router.navigateToMainPageWithSetStartDestination()
                    }

                    LoadState.Progress -> event.onProgress()
                    LoadState.Error -> event.onError()
                    LoadState.None -> {}
                }
            }
        }
    }

    class FactoryWrapperWithUseCases(
        private val loginUseCase: LoginUseCase,
        private val hasLoginUseCase: HasLoginUseCase
    ) {
        inner class Factory(
            private val router: Router
        ) : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AuthorizationVM(
                    router = router,
                    loginUseCase = loginUseCase,
                    hasLoginUseCase = hasLoginUseCase
                ) as T
            }
        }
    }
}