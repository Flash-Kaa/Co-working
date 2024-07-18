package com.na.coworking.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavScreen.MainPage.route
    ) {
        composable(NavScreen.MainPage.route) {}
        composable(NavScreen.PersonalAccount.route) {}
        composable("${NavScreen.Workspace.route}/{workspaceId}") {}
    }
}