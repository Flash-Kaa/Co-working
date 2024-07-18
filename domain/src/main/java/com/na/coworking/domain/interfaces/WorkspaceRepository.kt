package com.na.coworking.domain.interfaces

import kotlinx.coroutines.flow.StateFlow

interface WorkspaceRepository<T> {
    val flow: StateFlow<T>

    suspend fun fetch()
}