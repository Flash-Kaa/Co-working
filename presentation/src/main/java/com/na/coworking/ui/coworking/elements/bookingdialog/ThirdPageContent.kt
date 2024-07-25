package com.na.coworking.ui.coworking.elements.bookingdialog

import androidx.compose.foundation.background
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.na.coworking.R
import com.na.coworking.actions.CoworkingEvent
import com.na.coworking.domain.entities.LoadState
import com.na.coworking.domain.entities.WorkspaceObject
import com.na.coworking.ui.coworking.BookingStateUI
import com.na.coworking.ui.coworking.elements.CoworkingScheme
import com.na.coworking.ui.global.GExaText
import com.na.coworking.ui.global.RedButton

@Composable
internal fun ThirdPageContent(
    state: MutableState<BookingStateUI>,
    getTemplates: (BookingStateUI) -> List<WorkspaceObject>,
    onConfirm: () -> Unit,
    onPrevPage: () -> Unit,
    getEvent: (CoworkingEvent) -> Unit
) {
    GExaText(text = getChosenInfo(state), fontSize = 13.sp)
    Spacer(modifier = Modifier.height(20.dp))

    state.value.workspaceObject?.let { template ->
        CoworkingScheme(
            templates = getTemplates(state.value).map {
                it.copy(isEnableToChosen = it.id == template.id)
            }
        )

        Spacer(modifier = Modifier.height(20.dp))
    }

    NavButtons(state, onPrevPage, onConfirm, getEvent)
}

@Composable
private fun NavButtons(
    state: MutableState<BookingStateUI>,
    onPrevPage: () -> Unit,
    onConfirm: () -> Unit,
    getEvent: (CoworkingEvent) -> Unit
) {
    val loadState = remember {
        mutableStateOf(LoadState.None)
    }

    Row {
        RedButton(
            text = stringResource(R.string.back_str),
            onClick = {
                state.value = state.value.copy(workspaceObject = null)
                onPrevPage()
            },
            isEnabled = loadState.value == LoadState.None,
            fontSize = 13.sp,
            modifier = Modifier.weight(10f),
        )
        Spacer(modifier = Modifier.weight(1f))
        RedButton(
            text = stringResource(id = R.string.booking),
            onClick = {
                getEvent(
                    CoworkingEvent.Booking(
                        bookingData = state.value,
                        onSuccess = { loadState.value = LoadState.Successful },
                        onError = { loadState.value = LoadState.Error },
                        onProgress = { loadState.value = LoadState.Progress }
                    ),
                )
            },
            isEnabled = state.value.workspaceObject != null && loadState.value == LoadState.None,
            fontSize = 13.sp,
            modifier = Modifier.weight(10f)
        )
    }

    if (loadState.value == LoadState.Successful) {
        MessageDialog(stringResource(R.string.success_booking)) {
            loadState.value = LoadState.None
            onConfirm()
        }
    } else if (loadState.value == LoadState.Error) {
        MessageDialog(stringResource(R.string.error_booking)) {
            loadState.value = LoadState.None
        }
    }
}

@Composable
private fun MessageDialog(
    text: String,
    onDismiss: () -> Unit
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
            Spacer(modifier = Modifier.height(40.dp))
            GExaText(
                text = text,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(30.dp))
        }

        CancelButton(onDismiss)
    }
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
                contentDescription = stringResource(R.string.close_dialog),
                tint = colorResource(id = R.color.soft_black)
            )
        }
    }
}

@Composable
private fun getChosenInfo(state: MutableState<BookingStateUI>) = buildAnnotatedString {
    append(stringResource(R.string.your_chosen_is))

    withStyle(SpanStyle(color = colorResource(id = R.color.red))) {
        append(" ${state.value.date} ${state.value.timeStart} - ${state.value.timeEnd}, ")
        append("${state.value.workspaceObject?.template?.category}")
    }
}