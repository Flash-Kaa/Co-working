package com.na.coworking.ui.coworking

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.na.coworking.domain.entities.Workspace
import com.na.coworking.domain.usecases.listofcoworking.GetCoworkingByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class CoworkingVM(
    id: Int,

    private val getCoworkingByIdUseCase: GetCoworkingByIdUseCase
) : ViewModel() {
    // TODO: booking entity for ch
    private val bookingData: MutableState<String?> = mutableStateOf(null)
    private val coworking: MutableState<Workspace> = mutableStateOf(
        Workspace(-1, "", "", "", "")
    )

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val newCoworking = getCoworkingByIdUseCase(id)

            withContext(Dispatchers.Main) {
                coworking.value = newCoworking
            }
        }
    }

    class FactoryWrapperWithUseCases(
        private val getCoworkingByIdUseCase: GetCoworkingByIdUseCase
    ) {
        inner class Factory(
            private val id: Int
        ) : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CoworkingVM(
                    id = id,
                    getCoworkingByIdUseCase = getCoworkingByIdUseCase
                ) as T
            }
        }
    }
}