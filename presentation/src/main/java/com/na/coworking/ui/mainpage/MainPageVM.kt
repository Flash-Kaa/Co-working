package com.na.coworking.ui.mainpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.na.coworking.actions.MainPageAction
import com.na.coworking.domain.usecases.listofcoworking.GetListUseCase
import com.na.coworking.navigation.main.MainPageRouter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class MainPageVM(
    private val router: MainPageRouter,
    private val getListUseCase: GetListUseCase
) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            getListUseCase.fetch()
        }
    }

    fun getListOFCoworking() = getListUseCase()

    fun getAction(action: MainPageAction): () -> Unit {
        return when (action) {
            is MainPageAction.ToCoworking -> toCoworking(action.id)
        }
    }

    private fun toCoworking(id: Int): () -> Unit {
        return { router.navigateToCoworking(id) }
    }

    class FactoryWrapperWithUseCases(
        private val getListUseCase: GetListUseCase
    ) {
        inner class Factory(
            private val router: MainPageRouter
        ) : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainPageVM(
                    router = router,
                    getListUseCase = getListUseCase
                ) as T
            }
        }
    }
}