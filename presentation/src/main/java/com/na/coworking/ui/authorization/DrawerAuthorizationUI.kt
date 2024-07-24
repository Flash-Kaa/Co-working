package com.na.coworking.ui.authorization

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.na.coworking.actions.AuthorizationAction
import com.na.coworking.actions.AuthorizationEvent
import com.na.coworking.appComponent
import com.na.coworking.navigation.Router

@Composable
fun DrawerAuthorizationUI(
    router: Router
) {
    val viewModel: AuthorizationVM = viewModel(
        factory = LocalContext.current.appComponent
            .getAuthorizationVMSubcomponent()
            .provideFactoryWrapper()
            .Factory(router)
    )

    DrawerAuthorizationUI(
        userLogin = viewModel.state,
        getAction = viewModel::getAction,
        getEvent = viewModel::getEvent
    )
}

@Composable
private fun DrawerAuthorizationUI(
    userLogin: MutableState<UserLoginStateUI>,
    getAction: (AuthorizationAction) -> (() -> Unit),
    getEvent: (AuthorizationEvent) -> Unit
) {
    AuthorizationUI(
        userLogin = userLogin,
        getEvent = getEvent,
        getAction = getAction
    )
}