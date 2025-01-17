package com.na.coworking.ui.account

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationServices
import com.na.coworking.actions.AccountAction
import com.na.coworking.actions.AccountEvent
import com.na.coworking.domain.entities.Booking
import com.na.coworking.domain.entities.LoadState
import com.na.coworking.domain.entities.Location
import com.na.coworking.domain.usecases.account.GetUserUseCase
import com.na.coworking.domain.usecases.authorization.LogoutUseCase
import com.na.coworking.domain.usecases.bookings.BookingCancelUseCase
import com.na.coworking.domain.usecases.bookings.BookingConfirmUseCase
import com.na.coworking.domain.usecases.bookings.GetUserBookingsUseCase
import com.na.coworking.navigation.Router
import com.na.coworking.ui.account.UserStateUI.Companion.toStateUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class AccountVM(
    private val router: Router,

    private val bookingConfirmUseCase: BookingConfirmUseCase,
    private val bookingCancelUseCase: BookingCancelUseCase,
    private val bookingsUseCase: GetUserBookingsUseCase,

    private val logoutUseCase: LogoutUseCase,

    private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    val user = mutableStateOf(UserStateUI())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val newUserValue = getUserUseCase()
            launch {
                bookingsUseCase.invoke(newUserValue.id)
            }

            launch {
                withContext(Dispatchers.Main) {
                    user.value = newUserValue.toStateUI()
                }
            }
        }
    }

    fun getBookings(): Flow<List<Booking>> = bookingsUseCase.items

    fun getAction(action: AccountAction): () -> Unit {
        return when (action) {
            is AccountAction.OnExit -> ::exitAccount
        }
    }

    fun getEvent(event: AccountEvent) {
        when (event) {
            is AccountEvent.OnCancelBooking -> cancelBooking(event)
            is AccountEvent.OnConfirmBooking -> confirmBooking(event)
        }
    }

    private fun cancelBooking(event: AccountEvent.OnCancelBooking) {
        viewModelScope.launch {
            launch {
                bookingCancelUseCase(event.bookingId)
            }

            launch {
                val flow = bookingCancelUseCase.state
                executeEventFromFlow(flow, event)
            }
        }
    }

    private fun confirmBooking(event: AccountEvent.OnConfirmBooking) {
        viewModelScope.launch(Dispatchers.IO) {
            launch(Dispatchers.Main) {
                gettingPermission(event)
            }

            launch {
                val flow = bookingConfirmUseCase.state
                executeEventFromFlow(flow, event)
            }
        }
    }

    private fun gettingPermission(event: AccountEvent.OnConfirmBooking) {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                event.context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                sendLocation(event)
            }

            else -> event.requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun sendLocation(event: AccountEvent.OnConfirmBooking) {
        val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(event.context)

        try {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                useLocation(location, event)
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    private fun useLocation(
        location: android.location.Location?,
        event: AccountEvent.OnConfirmBooking
    ) {
        location?.let {
            viewModelScope.launch {
                bookingConfirmUseCase(
                    event.bookingId, Location(
                        latitude = location.latitude.toFloat(),
                        longitude = location.longitude.toFloat()
                    )
                )
            }
        }
    }

    private fun exitAccount() {
        router.navigateToAuthorizationWithSetStartDestination()
        viewModelScope.launch { logoutUseCase() }
    }

    private suspend fun executeEventFromFlow(flow: Flow<LoadState>, event: AccountEvent) {
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
        private val bookingConfirm: BookingConfirmUseCase,
        private val bookingCancel: BookingCancelUseCase,
        private val bookings: GetUserBookingsUseCase,
        private val logout: LogoutUseCase,
        private val getUserUseCase: GetUserUseCase
    ) {
        inner class Factory(
            private val router: Router
        ) : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AccountVM(
                    router = router,
                    bookingConfirmUseCase = bookingConfirm,
                    bookingCancelUseCase = bookingCancel,
                    bookingsUseCase = bookings,
                    logoutUseCase = logout,
                    getUserUseCase = getUserUseCase
                ) as T
            }
        }
    }
}