package com.na.coworking.ui.account.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.na.coworking.R
import com.na.coworking.actions.AccountAction
import com.na.coworking.domain.entities.Booking
import com.na.coworking.ui.global.GExaText

fun LazyListScope.bookings(
    bookings: List<Booking>,
    getAction: (AccountAction) -> (() -> Unit)
) {
    item { Spacer(modifier = Modifier.height(10.dp)) }

    item {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .background(colorResource(id = R.color.soft_white))
                .height(10.dp)
        )
    }

    items(bookings) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .background(colorResource(id = R.color.soft_white))
                .padding(horizontal = 10.dp)
        ) {
            BookingCard(it, getAction)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
private fun BookingCard(
    booking: Booking,
    getAction: (AccountAction) -> (() -> Unit)
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(10.dp)
    ) {
        // TODO: Booking icon
        CardImage()

        CardInfo(booking, getAction)
    }
}

@Composable
private fun CardInfo(
    booking: Booking,
    getAction: (AccountAction) -> (() -> Unit)
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DateWithNameRow(booking)

        Spacer(modifier = Modifier.height(5.dp))

        TimeWithButtonsRow(booking, getAction)
    }
}

@Composable
private fun TimeWithButtonsRow(
    booking: Booking,
    getAction: (AccountAction) -> (() -> Unit)
) {
    Row {
        TextWriter(
            text = "${booking.timeStart}-${booking.timeEnd}",
            modifier = Modifier.weight(2f)
        )

        ConfirmBooking(
            onClick = getAction(AccountAction.OnConfirmBooking(booking.id)),
            modifier = Modifier.weight(2f)
        )

        CancelBooking(
            onClick = getAction(AccountAction.OnCancelBooking(booking.id)),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun DateWithNameRow(booking: Booking) {
    Row {
        TextWriter(booking.date, Modifier.weight(1f))
        TextWriter(booking.name, Modifier.weight(1f))
    }
}

@Composable
private fun CardImage() {
    Box(
        modifier = Modifier
            .background(
                color = colorResource(id = R.color.red),
                shape = RoundedCornerShape(10.dp)
            )
            .size(60.dp)
    )
}

@Composable
private fun TextWriter(text: String, modifier: Modifier) {
    Spacer(modifier = Modifier.width(5.dp))
    GExaText(
        text = text,
        fontSize = 12.sp,
        modifier = modifier
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.light_gray),
                shape = RoundedCornerShape(5.dp)
            )
            .padding(7.dp)
    )
}

@Composable
private fun ConfirmBooking(
    onClick: () -> Unit,
    modifier: Modifier
) {
    Spacer(modifier = Modifier.width(5.dp))
    GExaText(
        text = stringResource(R.string.confirm),
        color = colorResource(id = R.color.white),
        fontSize = 12.sp,
        textAlign = TextAlign.Center,
        modifier = modifier
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true, color = Color.Gray)
            )
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.red),
                shape = RoundedCornerShape(5.dp)
            )
            .padding(7.dp)
    )
}

@Composable
private fun CancelBooking(
    onClick: () -> Unit,
    modifier: Modifier
) {
    Spacer(modifier = Modifier.width(5.dp))

    Icon(
        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_close_24),
        contentDescription = stringResource(R.string.cancel_booking_cd),
        tint = colorResource(id = R.color.white),
        modifier = modifier
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true, color = Color.Gray)
            )
            .background(
                color = colorResource(id = R.color.soft_gray),
                shape = RoundedCornerShape(5.dp)
            )
    )
}