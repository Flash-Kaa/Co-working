package com.na.coworking.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.na.coworking.ui.account.DrawerAccountUI
import com.na.coworking.ui.account.elements.Page
import com.na.coworking.ui.authorization.DrawerAuthorizationUI
import com.na.coworking.ui.coworking.DrawerCoworkingUI
import com.na.coworking.ui.mainpage.DrawerMainUI

private val coworkingIdArguments = listOf(
    navArgument("workspaceId") { type = NavType.IntType }
)

@Composable
fun NavGraph(
    navController: NavHostController,
    padding: PaddingValues
) {
    val router = remember { Router(navController) }

    NavHost(
        navController = navController,
        startDestination = NavScreen.Authorization.route
    ) {
        composable(NavScreen.Authorization.route) { DrawerAuthorizationUI(router) }
        composable(NavScreen.MainPage.route) { DrawerMainUI(router, padding) }
        composable(NavScreen.Contacts.route) {}

        composable(NavScreen.PersonalAccount.route) {
            DrawerAccountUI(Page.Profile, router, padding)
        }
        composable(NavScreen.ListOfCoworking.route) {
            DrawerAccountUI(Page.Booking, router, padding)
        }

        composable(
            "${NavScreen.Workspace.route}/{workspaceId}", coworkingIdArguments
        ) {
            val id = it.arguments?.getInt("workspaceId") ?: 0
            DrawerCoworkingUI(id = id, paddingValues = padding)
        }
    }
}