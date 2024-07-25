package com.na.coworking.ui.coworking.elements.bookingdialog

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
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
fun SecondPAgeContent(
    state: MutableState<BookingStateUI>,
    getTemplates: (BookingStateUI) -> List<WorkspaceObject>,
    onNextPage: () -> Unit,
    onPrevPage: () -> Unit
) {
    GExaText(text = stringResource(R.string.choose_place), fontSize = 13.sp)
    Spacer(modifier = Modifier.height(20.dp))

    val templates = remember { getTemplates(state.value) }
    CoworkingScheme(templates = templates) {
        state.value = state.value.copy(workspaceObject = it)
    }

    ChosenInfo(state)

    NavButtons(state, onPrevPage, onNextPage)
}

@Composable
private fun NavButtons(
    state: MutableState<BookingStateUI>,
    onPrevPage: () -> Unit,
    onNextPage: () -> Unit
) {
    Row {
        RedButton(
            text = stringResource(R.string.back_str),
            onClick = {
                state.value = state.value.copy(workspaceObject = null)
                onPrevPage()
            },
            isEnabled = true,
            modifier = Modifier.weight(10f)
        )
        Spacer(modifier = Modifier.weight(1f))
        RedButton(
            text = stringResource(id = R.string.continue_str),
            onClick = onNextPage,
            isEnabled = state.value.workspaceObject != null,
            modifier = Modifier.weight(10f)
        )
    }
}

@Composable
private fun ChosenInfo(state: MutableState<BookingStateUI>) {
    state.value.workspaceObject?.let {
        Spacer(modifier = Modifier.height(20.dp))
        GExaText(text = getChooseText(it), fontSize = 13.sp)
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
private fun getChooseText(template: WorkspaceObject) = buildAnnotatedString {
    withStyle(SpanStyle(color = colorResource(id = R.color.red))) {
        append(stringResource(R.string.choosed))
    }

    append(template.template.category)
}