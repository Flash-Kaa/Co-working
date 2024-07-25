package com.na.coworking.ui.coworking.elements.bookingdialog

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.na.coworking.R
import com.na.coworking.domain.entities.WorkspaceObject
import com.na.coworking.ui.coworking.BookingStateUI
import com.na.coworking.ui.coworking.elements.CoworkingScheme
import com.na.coworking.ui.global.GExaText
import com.na.coworking.ui.global.RedButton

@Composable
fun ThirdPageContent(
    state: MutableState<BookingStateUI>,
    getTemplates: (BookingStateUI) -> List<WorkspaceObject>,
    onConfirm: () -> Unit,
    onPrevPage: () -> Unit
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

    NavButtons(state, onPrevPage, onConfirm)
}

@Composable
private fun NavButtons(
    state: MutableState<BookingStateUI>,
    onPrevPage: () -> Unit,
    onConfirm: () -> Unit
) {
    Row {
        RedButton(
            text = stringResource(R.string.back_str),
            onClick = {
                state.value = state.value.copy(workspaceObject = null)
                onPrevPage()
            },
            isEnabled = true,
            fontSize = 13.sp,
            modifier = Modifier.weight(10f)
        )
        Spacer(modifier = Modifier.weight(1f))
        RedButton(
            text = stringResource(id = R.string.booking),
            onClick = onConfirm,
            isEnabled = state.value.workspaceObject != null,
            fontSize = 13.sp,
            modifier = Modifier.weight(10f)
        )
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