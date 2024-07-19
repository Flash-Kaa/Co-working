package com.na.coworking.actions

sealed class MainPageAction {
    data class ToCoworking(val id: Int) : MainPageAction()
}