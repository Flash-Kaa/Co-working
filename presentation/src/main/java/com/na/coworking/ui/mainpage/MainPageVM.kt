package com.na.coworking.ui.mainpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.na.coworking.actions.MainPageAction
import com.na.coworking.domain.usecases.listofcoworking.GetListOfCoworkingUseCase
import com.na.coworking.navigation.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class MainPageVM(
    private val router: Router,
    private val getListOfCoworkingUseCase: GetListOfCoworkingUseCase
) : ViewModel() {
    init {
        viewModelScope.launch {
            getListOfCoworkingUseCase.fetch()
        }
    }

    fun getListOFCoworking() = getListOfCoworkingUseCase()

    fun getAction(action: MainPageAction): () -> Unit {
        return when (action) {
            is MainPageAction.ToCoworking -> toCoworking(action.id)
        }
    }

    private fun toCoworking(id: Int): () -> Unit {
        return { router.navigateToCoworking(id) }
    }

    class FactoryWrapperWithUseCases(
        private val getListOfCoworkingUseCase: GetListOfCoworkingUseCase
    ) {
        inner class Factory(
            private val router: Router
        ) : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainPageVM(
                    router = router,
                    getListOfCoworkingUseCase = getListOfCoworkingUseCase
                ) as T
            }
        }
    }
}