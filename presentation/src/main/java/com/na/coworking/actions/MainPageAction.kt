package com.na.coworking.actions

sealed class MainPageAction {
    // Navigation
    data class ToCoworking(val id: Int) : MainPageAction()
}