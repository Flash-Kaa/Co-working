package com.na.coworking.navigation.main

sealed class MainNavScreen(val route: String) {
    data object Workspace : MainNavScreen("page_of_workspace") {
        fun getNavigatePath(id: Int) = "$route/$id"
    }
}