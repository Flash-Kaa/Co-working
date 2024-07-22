package com.na.coworking.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.na.coworking.actions.GlobalAction
import com.na.coworking.navigation.NavGraph
import com.na.coworking.navigation.Router
import com.na.coworking.ui.global.GlobalViewModel
import com.na.coworking.ui.global.TopAppBar

@Composable
internal fun DrawerAppUI() {
    val navController = rememberNavController()
    val router = Router(navController)
    val viewModel: GlobalViewModel = viewModel(
        factory = GlobalViewModel.Factory(router)
    )

    DrawerAppUI(viewModel::getAction, navController)
}

@Composable
private fun DrawerAppUI(
    getAction: (GlobalAction) -> () -> Unit,
    navController: NavHostController
) {
    Scaffold(
        topBar = { TopAppBar(getAction = getAction) },
        content = { NavGraph(navController, it) }
    )
}