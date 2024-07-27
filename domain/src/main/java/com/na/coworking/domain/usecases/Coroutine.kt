package com.na.coworking.domain.usecases

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

suspend fun runWithSupervisorInBackground(
    onErrorAction: (() -> Unit)? = null,

    content: suspend CoroutineScope.() -> Unit
) {
    supervisorScope {
        launch(
            context = Dispatchers.IO + CoroutineExceptionHandler { _, exception ->
                exception.printStackTrace()
                onErrorAction?.invoke()
            },
            block = content
        )
    }
}