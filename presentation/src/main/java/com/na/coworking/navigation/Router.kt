package com.na.coworking.navigation

import androidx.navigation.NavController

class Router(
    private val navController: NavController
) {
    fun navigateToMainPage() = navController.navigate(NavScreen.MainPage.route)

    fun navigateToPersonalAccount() = navController.navigate(NavScreen.PersonalAccount.route)

    fun navigateToWorkspacePage(id: Int) =
        navController.navigate(NavScreen.Workspace.getNavigatePath(id))
}