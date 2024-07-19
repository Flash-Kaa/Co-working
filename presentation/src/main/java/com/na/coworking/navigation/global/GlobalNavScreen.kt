package com.na.coworking.navigation.global

sealed class GlobalNavScreen(val route: String) {
    data object MainPage : GlobalNavScreen("main_page")

    data object PersonalAccount : GlobalNavScreen("personal_account_aka_lk")

    data object Contacts : GlobalNavScreen("contacts_page")

    data object ListOfCoworking : GlobalNavScreen("list_of_coworking")
}