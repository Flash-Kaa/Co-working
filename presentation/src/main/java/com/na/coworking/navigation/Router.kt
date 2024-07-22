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