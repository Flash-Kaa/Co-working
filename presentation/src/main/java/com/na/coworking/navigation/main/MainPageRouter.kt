package com.na.coworking.navigation.main

import androidx.navigation.NavController
import com.na.coworking.navigation.global.GlobalRouter

class MainPageRouter(
    private val navController: NavController
) : GlobalRouter(navController) {
    fun navigateToCoworking(id: Int) {
        navController.navigate(MainNavScreen.Workspace.getNavigatePath(id))
    }
}