package com.na.coworking.navigation.global

import androidx.navigation.NavController

open class GlobalRouter(
    private val navController: NavController
) {
    fun navigateToMainPage() {
        navController.navigate(GlobalNavScreen.MainPage.route) {
            popUpTo(0)
        }
    }

    fun navigateToPersonalAccount() {
        navController.navigate(GlobalNavScreen.PersonalAccount.route)
    }

    fun navigateToContacts() {
        navController.navigate(GlobalNavScreen.Contacts.route)
    }

    fun navigateToListOfCoworking() {
        navController.navigate(GlobalNavScreen.ListOfCoworking.route)
    }
}