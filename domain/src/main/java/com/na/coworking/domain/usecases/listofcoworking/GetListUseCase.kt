package com.na.coworking.domain.usecases.listofcoworking

import com.na.coworking.domain.entities.Workspace
import com.na.coworking.domain.interfaces.listofcowowking.ListOfCoworkingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GetListUseCase(
    private val repository: ListOfCoworkingRepository
) {
    private val _items = MutableStateFlow<List<Workspace>>(emptyList())
    val items = _items.asStateFlow()

    operator fun invoke(): Flow<List<Workspace>> = items

    suspend fun fetch() {
        repository.getList().collect { list ->
            _items.update { list }
        }
    }
}