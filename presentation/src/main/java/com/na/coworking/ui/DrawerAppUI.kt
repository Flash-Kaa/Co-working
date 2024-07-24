package com.na.coworking.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.na.coworking.actions.GlobalAction
import com.na.coworking.navigation.NavGraph
import com.na.coworking.navigation.Router
import com.na.coworking.ui.global.GlobalViewModel
import com.na.coworking.ui.global.LeftSideBar
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
    val sizeBarIsOpen = remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = { TopAppBar(getAction = getAction, isOpen = sizeBarIsOpen) }
    ) {
        NavGraph(navController, it)
    }

    SideBarOpener(sizeBarIsOpen, getAction)
}

@Composable
private fun SideBarOpener(
    topAppBarIsOpen: MutableState<Boolean>,
    getAction: (GlobalAction) -> () -> Unit
) {
    if (topAppBarIsOpen.value) {
        LeftSideBar(isOpen = topAppBarIsOpen, getAction = getAction)
    }
}