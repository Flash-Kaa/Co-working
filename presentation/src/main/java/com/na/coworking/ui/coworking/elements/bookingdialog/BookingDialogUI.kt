package com.na.coworking.ui.coworking.elements.bookingdialog

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.na.coworking.R
import com.na.coworking.domain.entities.WorkspaceObject
import com.na.coworking.ui.coworking.BookingStateUI
import com.na.coworking.ui.global.GExaText
import kotlinx.coroutines.launch
import kotlin.math.abs

private const val PROGRESS_BAR_PARTS_COUNT = 3
private const val PROGRESS_BAR_STEP = 1f

@Composable
fun BookingDialogUI(
    state: MutableState<BookingStateUI>,
    timesRangesToBooking: List<String>,
    daysToBooking: List<String>,
    getTemplates: (BookingStateUI) -> List<WorkspaceObject>,
    getTimes: (String, String) -> List<List<Pair<String, String>>>,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        val progressBar = remember { Animatable(PROGRESS_BAR_STEP) }

        Column(
            modifier = Modifier
                .shadow(2.dp, RoundedCornerShape(10.dp))
                .background(
                    color = colorResource(id = R.color.white),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(16.dp)
        ) {
            DialogTitle(progressBar)

            Pager(
                progressBar = progressBar,
                state = state,
                timesRangesToBooking = timesRangesToBooking,
                daysToBooking = daysToBooking,
                getTimes = getTimes,
                getTemplates = getTemplates,
                onConfirm = onConfirm
            )
        }

        CancelButton(onDismiss)
    }
}

@Composable
private fun Pager(
    progressBar: Animatable<Float, AnimationVector1D>,
    state: MutableState<BookingStateUI>,
    timesRangesToBooking: List<String>,
    daysToBooking: List<String>,
    getTimes: (String, String) -> List<List<Pair<String, String>>>,
    getTemplates: (BookingStateUI) -> List<WorkspaceObject>,
    onConfirm: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    if (abs(progressBar.value - PROGRESS_BAR_STEP) < 1e-3) {
        FirstPageContent(state, timesRangesToBooking, daysToBooking, getTimes) {
            scope.launch {
                progressBar.animateTo(
                    targetValue = 2 * PROGRESS_BAR_STEP,
                    animationSpec = tween(durationMillis = 600)
                )
            }
        }
    } else if (abs(progressBar.value - 2 * PROGRESS_BAR_STEP) < 1e-3) {
        SecondPAgeContent(
            state = state,
            getTemplates = getTemplates,
            onNextPage = {
                scope.launch {
                    progressBar.animateTo(
                        targetValue = 3 * PROGRESS_BAR_STEP,
                        animationSpec = tween(durationMillis = 600)
                    )
                }
            },
            onPrevPage = {
                scope.launch {
                    progressBar.animateTo(
                        targetValue = 1 * PROGRESS_BAR_STEP,
                        animationSpec = tween(durationMillis = 200)
                    )
                }
            }
        )
    } else if (abs(progressBar.value - 3 * PROGRESS_BAR_STEP) < 1e-3) {
        ThirdPageContent(
            state = state,
            getTemplates = getTemplates,
            onConfirm = onConfirm,
            onPrevPage = {
                scope.launch {
                    progressBar.animateTo(
                        targetValue = 2 * PROGRESS_BAR_STEP,
                        animationSpec = tween(durationMillis = 200)
                    )
                }
            }
        )
    }
}

@Composable
private fun DialogTitle(progressBar: Animatable<Float, AnimationVector1D>) {
    GExaText(
        text = stringResource(R.string.booking_place_now),
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(20.dp))
    ProgressBar(progressBar)
}

@Composable
private fun ProgressBar(progressBar: Animatable<Float, AnimationVector1D>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.light_gray),
                shape = RoundedCornerShape(50)
            )
    ) {
        Box(
            modifier = Modifier
                .height(5.dp)
                .fillMaxWidth(progressBar.value / PROGRESS_BAR_PARTS_COUNT)
                .background(
                    color = colorResource(id = R.color.red),
                    shape = RoundedCornerShape(50)
                )
        )
    }

    Spacer(modifier = Modifier.height(10.dp))
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

@Preview(showBackground = true)
@Composable
private fun PreviewDialog() {
    val wot = WorkspaceObject.Template(0, "", "cat", false)

    val templates = remember {
        mutableStateOf(
            listOf(
                WorkspaceObject(0, 0, 0, 100, 100, wot.copy(category = "red")),
                WorkspaceObject(1, 100, 0, 100, 100, wot.copy(category = "blue")),
                WorkspaceObject(1, 0, 100, 100, 100, wot.copy(category = "blue")),
                WorkspaceObject(2, 100, 100, 200, 200, wot.copy(category = "green")),
                WorkspaceObject(1, 310, 200, 110, 100, wot.copy(category = "blue")),
            )
        )
    }

    val state = remember {
        mutableStateOf(BookingStateUI())
    }

    Box(modifier = Modifier.fillMaxSize()) {
        BookingDialogUI(
            onDismiss = {},
            getTemplates = { templates.value },
            state = state,
            timesRangesToBooking = listOf("45 мин", "1 час", "2 часа"),
            daysToBooking = listOf(
                "25.07.2024",
                "26.07.2024",
                "27.07.2024",
                "25.07.2024",
                "26.07.2024",
                "27.07.2024"
            ),
            getTimes = { _, _ ->
                listOf(
                    listOf("12:00" to "13:00", "13:00" to "14:00", "14:00" to "15:00"),
                    listOf("15:00" to "16:00", "16:00" to "17:00", "17:00" to "18:00"),
                    listOf("18:00" to "19:00", "19:00" to "20:00", "20:00" to "21:00"),
                    listOf("21:00" to "22:00"),
                )
            },
            onConfirm = {}
        )
    }
}