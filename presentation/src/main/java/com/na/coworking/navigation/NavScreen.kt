package com.na.coworking.navigation

sealed class NavScreen(val route: String) {
    data object Authorization : NavScreen("authorization_page")

    data object MainPage : NavScreen("main_page")

    data object PersonalAccount : NavScreen("personal_account_aka_lk")

    data object Contacts : NavScreen("contacts_page")

    data object ListOfCoworking : NavScreen("list_of_coworking")

    data object Workspace : NavScreen("page_of_workspace") {
        fun getNavigatePath(id: Int) = "$route/$id"
    }
}