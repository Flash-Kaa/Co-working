package com.na.coworking.ui.account.elements

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.na.coworking.R
import com.na.coworking.actions.AccountEvent
import com.na.coworking.domain.entities.LoadState
import com.na.coworking.ui.global.GExaText
import com.na.coworking.ui.global.RedButton

@Composable
internal fun ConfirmBookingDialog(
    bookingId: Int,
    onDismiss: () -> Unit,
    onEvent: (AccountEvent) -> Unit
) {
    val state = remember { mutableStateOf(LoadState.None) }

    Dialog(
        onDismissRequest = onDismiss
    ) {
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

            if (state.value == LoadState.None || state.value == LoadState.Progress) {
                BaseResultContent(bookingId, state, onEvent)
            } else {
                LoadResultContent(state)
            }
        }

        CancelButton(onDismiss)
    }
}

@Composable
private fun Title() {
    GExaText(
        text = stringResource(id = R.string.confirm_booking),
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(40.dp))
}

@Composable
private fun BaseResultContent(
    bookingId: Int,
    state: MutableState<LoadState>,
    onEvent: (AccountEvent) -> Unit,
) {
    val context = LocalContext.current
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { }
    )

    DialogDescription()
    RedButton(
        text = stringResource(R.string.check_location),
        modifier = Modifier.fillMaxWidth(),
        isEnabled = state.value != LoadState.Progress,
        onClick = {
            onEvent(
                AccountEvent.OnConfirmBooking(
                    bookingId = bookingId,
                    requestPermissionLauncher = requestPermissionLauncher,
                    context = context,
                    onSuccess = {
                        state.value = LoadState.Successful
                    },
                    onError = {
                        state.value = LoadState.Error
                    },
                    onProgress = {
                        state.value = LoadState.Progress
                    }
                )
            )
        }
    )
}

@Composable
private fun DialogDescription() {
    Icon(
        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_location_pin_24),
        contentDescription = stringResource(R.string.check_location_description),
        tint = colorResource(id = R.color.red),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    )

    GExaText(
        text = stringResource(R.string.check_location_description),
        fontSize = 16.sp,
        fontWeight = FontWeight(400),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(40.dp))
}

@Composable
private fun LoadResultContent(state: MutableState<LoadState>) {
    GExaText(
        text = stringResource(
            if (state.value == LoadState.Successful) {
                R.string.booking_confirmed_success
            } else {
                R.string.booking_confirmed_error
            }
        ),
        fontSize = 16.sp,
        fontWeight = FontWeight(400),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
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
                contentDescription = stringResource(R.string.cancel_confirm),
                tint = colorResource(id = R.color.soft_black)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Box(Modifier.fillMaxSize()) {
        ConfirmBookingDialog(
            onDismiss = { },
            bookingId = 0,
            onEvent = { it.onSuccess() }
        )
    }
}