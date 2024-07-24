package com.na.coworking.actions

internal sealed class MainPageAction {
    // Navigation
    data class ToCoworking(val id: Int) : MainPageAction()
}