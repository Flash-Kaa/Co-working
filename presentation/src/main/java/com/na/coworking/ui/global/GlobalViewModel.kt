package com.na.coworking.ui.global

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.na.coworking.actions.GlobalAction
import com.na.coworking.navigation.Router

internal class GlobalViewModel(
    private val router: Router
) : ViewModel() {
    fun getAction(action: GlobalAction): () -> Unit {
        return when (action) {
            GlobalAction.ToContacts -> ::toContacts
            GlobalAction.ToListOfCoworking -> ::toListOfCoworking
            GlobalAction.ToMainPage -> ::toMainPage
            GlobalAction.ToPersonAccount -> ::toPersonalAccount
        }
    }

    private fun toContacts() {
        router.navigateToContacts()
    }

    private fun toListOfCoworking() {
        router.navigateToListOfCoworking()
    }

    private fun toMainPage() {
        router.navigateToMainPage()
    }

    private fun toPersonalAccount() {
        router.navigateToPersonalAccount()
    }

    class Factory(private val router: Router) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return GlobalViewModel(router) as T
        }
    }
}