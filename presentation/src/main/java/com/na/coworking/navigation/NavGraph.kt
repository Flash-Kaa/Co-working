package com.na.coworking.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.na.coworking.navigation.global.GlobalNavScreen
import com.na.coworking.navigation.main.MainNavScreen
import com.na.coworking.navigation.main.MainPageRouter
import com.na.coworking.ui.mainpage.DrawerMainUI

private val coworkingIdArguments = listOf(
    navArgument("workspaceId") { type = NavType.IntType }
)

@Composable
fun NavGraph(
    navController: NavHostController,
    padding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = GlobalNavScreen.MainPage.route
    ) {
        composable(GlobalNavScreen.MainPage.route) {
            DrawerMainUI(MainPageRouter(navController), padding)
        }
        composable(GlobalNavScreen.PersonalAccount.route) {}
        composable(GlobalNavScreen.ListOfCoworking.route) {}
        composable(GlobalNavScreen.Contacts.route) {}

        composable(
            "${MainNavScreen.Workspace.route}/{workspaceId}", coworkingIdArguments
        ) { }
    }
}