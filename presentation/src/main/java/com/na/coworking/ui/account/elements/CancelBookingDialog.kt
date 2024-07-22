package com.na.coworking.ui.account.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.na.coworking.R
import com.na.coworking.actions.AccountEvent
import com.na.coworking.domain.entities.LoadState
import com.na.coworking.ui.global.GExaText
import com.na.coworking.ui.global.RedButton

@Composable
fun CancelBookingDialog(
    bookingId: Int,
    onDismiss: () -> Unit,
    onError: () -> Unit,
    onSuccess: () -> Unit,
    onEvent: (AccountEvent) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .shadow(2.dp, RoundedCornerShape(10.dp))
                .background(
                    color = colorResource(id = R.color.white),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(16.dp)
        ) {
            Title()
            TextContent()

            AnswerButtons(bookingId, onDismiss, onError, onSuccess, onEvent)
        }

        CancelButton(onDismiss)
    }
}

@Composable
private fun AnswerButtons(
    bookingId: Int,
    onDismiss: () -> Unit,
    onError: () -> Unit,
    onSuccess: () -> Unit,
    onEvent: (AccountEvent) -> Unit
) {
    val spacerWeight = 1f
    val buttonWeight = 4f

    val state = remember {
        mutableStateOf(LoadState.None)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Spacer(modifier = Modifier.weight(spacerWeight))

        RedButton(
            text = stringResource(id = R.string.yes),
            onClick = {
                onEvent(
                    AccountEvent.OnCancelBooking(
                        bookingId = bookingId,
                        onSuccess = {
                            state.value = LoadState.Successful
                            onSuccess()
                        },
                        onError = {
                            state.value = LoadState.Error
                            onError()
                        },
                        onProgress = {
                            state.value = LoadState.Progress
                        }
                    )
                )
            },
            modifier = Modifier.weight(buttonWeight),
            isEnabled = state.value != LoadState.Progress
        )

        Spacer(modifier = Modifier.weight(spacerWeight))

        RedButton(
            text = stringResource(id = R.string.no),
            onClick = onDismiss,
            modifier = Modifier.weight(buttonWeight),
            isEnabled = state.value != LoadState.Progress
        )

        Spacer(modifier = Modifier.weight(spacerWeight))
    }

    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
private fun TextContent() {
    GExaText(
        text = stringResource(R.string.are_your_want_to_cancal_booking),
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
private fun Title() {
    GExaText(
        text = stringResource(R.string.are_your_shoure),
        fontSize = 16.sp,
        color = colorResource(R.color.red),
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
private fun CancelButton(onDismiss: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        IconButton(onClick = onDismiss) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_close_24),
                contentDescription = stringResource(R.string.not_cancel),
                tint = colorResource(id = R.color.soft_black)
            )
        }
    }
}