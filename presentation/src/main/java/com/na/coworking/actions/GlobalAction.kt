package com.na.coworking.actions

sealed class GlobalAction {
    // Navigation
    data object ToPersonAccount : GlobalAction()
    data object ToListOfCoworking : GlobalAction()
    data object ToContacts : GlobalAction()
    data object ToMainPage : GlobalAction()
}