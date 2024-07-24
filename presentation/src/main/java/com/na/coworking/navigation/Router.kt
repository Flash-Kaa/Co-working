package com.na.coworking.navigation

import androidx.navigation.NavController

open class Router(
    private val navController: NavController
) {
    fun navigateToMainPage() {
        navController.navigate(NavScreen.MainPage.route) {
            popUpTo(0)
        }
    }

    fun navigateToMainPageWithSetStartDestination() {
        navController.graph.setStartDestination(NavScreen.MainPage.route)
        navController.navigate(NavScreen.MainPage.route) {
            popUpTo(0)
        }
    }

    fun navigateToAuthorizationWithSetStartDestination() {
        navController.graph.setStartDestination(NavScreen.Authorization.route)
        navController.navigate(NavScreen.Authorization.route) {
            popUpTo(0)
        }
    }

    fun navigateToPersonalAccount() {
        navController.navigate(NavScreen.PersonalAccount.route)
    }

    fun navigateToContacts() {
        navController.navigate(NavScreen.Contacts.route)
    }

    fun navigateToListOfCoworking() {
        navController.navigate(NavScreen.ListOfCoworking.route)
    }

    fun navigateToCoworking(id: Int) {
        navController.navigate(NavScreen.Workspace.getNavigatePath(id))
    }
}