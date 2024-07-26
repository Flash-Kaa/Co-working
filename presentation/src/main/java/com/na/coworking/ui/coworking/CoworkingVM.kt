package com.na.coworking.ui.coworking

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.na.coworking.actions.CoworkingEvent
import com.na.coworking.domain.entities.CoworkingBooking
import com.na.coworking.domain.entities.LoadState
import com.na.coworking.domain.entities.Workspace
import com.na.coworking.domain.entities.WorkspaceObject
import com.na.coworking.domain.usecases.bookings.AddBookingUseCase
import com.na.coworking.domain.usecases.bookings.GetCoworkingBookingsUseCase
import com.na.coworking.domain.usecases.bookings.GetFreeTimesUseCase
import com.na.coworking.domain.usecases.bookings.GetTemplatesUseCase
import com.na.coworking.domain.usecases.listofcoworking.GetCoworkingByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal class CoworkingVM(
    id: Int,
    getCoworkingBookingsUseCase: GetCoworkingBookingsUseCase,

    private val getCoworkingByIdUseCase: GetCoworkingByIdUseCase,
    private val addBookingUseCase: AddBookingUseCase,
    private val getFreeTimesUseCase: GetFreeTimesUseCase,
    private val getTemplatesUseCase: GetTemplatesUseCase
) : ViewModel() {
    val coworking: MutableState<Workspace> = mutableStateOf(
        Workspace(-1, "", "", "", "")
    )

    private val bookings = getCoworkingBookingsUseCase.items
    private val dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    val days = List<String>(7) {
        LocalDate.now().plusDays((1 + it).toLong()).format(dtFormatter)
    }

    val timesRange = listOf("45 мин", "1 час", "2 часа")

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                val newCoworking = getCoworkingByIdUseCase(id)

                withContext(Dispatchers.Main) {
                    coworking.value = newCoworking
                }
            }

            launch {
                getCoworkingBookingsUseCase(id)
            }
        }
    }

    fun getTimes(date: String, time: String): List<List<Pair<String, String>>> {
        return getFreeTimesUseCase(date, time, coworking.value, bookings.value)
    }

    fun getTemplates(state: BookingStateUI): List<WorkspaceObject> {
        return getTemplatesUseCase(
            state.timeStart,
            state.timeEnd,
            state.date,
            coworking.value,
            bookings.value
        )
    }

    fun getEvent(event: CoworkingEvent) {
        when (event) {
            is CoworkingEvent.Booking -> booking(event)
        }
    }

    fun booking(event: CoworkingEvent.Booking) {
        event.bookingData.workspaceObject?.let {
            val coworking = coworking.value

            executeBooking(it, coworking, event)
        }
    }

    private fun executeBooking(
        it: WorkspaceObject,
        coworking: Workspace,
        event: CoworkingEvent.Booking
    ) = viewModelScope.launch(Dispatchers.IO) {
        launch {
            val bookingData = CoworkingBooking(
                idObject = it.id,
                idWorkspace = coworking.id,
                timeStart = event.bookingData.timeStart,
                timeEnd = event.bookingData.timeEnd,
                date = event.bookingData.date,
                isConfirmed = false
            )

            addBookingUseCase(bookingData)
        }

        launch {
            executeFromFlow(addBookingUseCase.state, event)
        }
    }

    private suspend fun executeFromFlow(flow: Flow<LoadState>, event: CoworkingEvent) {
        flow.collectLatest {
            withContext(Dispatchers.Main) {
                when (it) {
                    LoadState.Successful -> event.onSuccess()
                    LoadState.Progress -> event.onProgress()
                    LoadState.Error -> event.onError()
                    LoadState.None -> {}
                }
            }
        }
    }

    class FactoryWrapperWithUseCases(
        private val getCoworkingByIdUseCase: GetCoworkingByIdUseCase,
        private val getCoworkingBookingsUseCase: GetCoworkingBookingsUseCase,
        private val addBookingUseCase: AddBookingUseCase,
        private val getFreeTimesUseCase: GetFreeTimesUseCase,
        private val getTemplatesUseCase: GetTemplatesUseCase
    ) {
        inner class Factory(
            private val id: Int
        ) : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CoworkingVM(
                    id = id,
                    getCoworkingByIdUseCase = getCoworkingByIdUseCase,
                    getCoworkingBookingsUseCase = getCoworkingBookingsUseCase,
                    addBookingUseCase = addBookingUseCase,
                    getTemplatesUseCase = getTemplatesUseCase,
                    getFreeTimesUseCase = getFreeTimesUseCase
                ) as T
            }
        }
    }
}