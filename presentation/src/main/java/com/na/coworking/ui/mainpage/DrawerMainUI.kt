package com.na.coworking.ui.mainpage

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.na.coworking.actions.MainPageAction
import com.na.coworking.appComponent
import com.na.coworking.domain.entities.Workspace
import com.na.coworking.navigation.Router

@Composable
fun DrawerMainUI(
    router: Router,
    padding: PaddingValues
) {
    val viewModel: MainPageVM = viewModel(
        factory = LocalContext.current.appComponent
            .provideTokenUseCasesComponent()
            .getMainPageVMSubcomponent()
            .provideFactoryWrapper()
            .Factory(router)
    )

    val state = viewModel.getListOFCoworking().collectAsState(emptyList())

    DrawerMainUI(padding, state, viewModel::getAction)
}

@Composable
private fun DrawerMainUI(
    padding: PaddingValues,
    state: State<List<Workspace>>,
    getAction: (MainPageAction) -> (() -> Unit)
) {
    MainPageUI(padding, state, getAction)
}