package com.na.coworking.ui.account.elements

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.na.coworking.R

enum class Page {
    Booking, Profile;

    @Composable
    fun title(): String {
        return when (this) {
            Booking -> stringResource(R.string.my_bookings)
            Profile -> stringResource(R.string.profile)
        }
    }
}