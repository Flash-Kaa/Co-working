package com.na.coworking.ui.coworking.elements.bookingdialog

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.na.coworking.R
import com.na.coworking.ui.coworking.BookingStateUI
import com.na.coworking.ui.global.GExaText
import com.na.coworking.ui.global.RedButton

@Composable
fun FirstPageContent(
    state: MutableState<BookingStateUI>,
    timesRangesToBooking: List<String>,
    daysToBooking: List<String>,
    getTimes: (String, String) -> List<List<Pair<String, String>>>,
    onNextPage: () -> Unit
) {
    val dateMenuIsOpen = remember { mutableStateOf(false) }
    val timeMenuIsOpen = remember { mutableStateOf(false) }
    val date = remember { mutableStateOf(daysToBooking.first()) }
    val time = remember { mutableStateOf(timesRangesToBooking.first()) }

    DateTimeChooser(
        state = state,
        date = date,
        dateMenuIsOpen = dateMenuIsOpen,
        time = time,
        timeMenuIsOpen = timeMenuIsOpen,
        daysToBooking = daysToBooking,
        timesRangesToBooking = timesRangesToBooking,
        getTimes = getTimes
    )

    val canNext = state.value.timeStart.isNotBlank() && state.value.timeEnd.isNotBlank()
    ChooseInfo(canNext, state)
    NextButton(onNextPage, canNext)
}

@Composable
private fun NextButton(onNextPage: () -> Unit, canNext: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        RedButton(
            text = stringResource(R.string.continue_str),
            onClick = onNextPage,
            isEnabled = canNext,
            fontSize = 13.sp,
            modifier = Modifier.fillMaxWidth(0.45f)
        )
    }
}

@Composable
private fun ChooseInfo(
    canNext: Boolean,
    state: MutableState<BookingStateUI>
) {
    Spacer(modifier = Modifier.height(5.dp))
    if (canNext) {
        GExaText(text = getChooseText(state), fontSize = 13.sp)
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun getChooseText(state: MutableState<BookingStateUI>) = buildAnnotatedString {
    withStyle(SpanStyle(color = colorResource(id = R.color.red))) {
        append(stringResource(R.string.choosed))
    }

    state.value.also {
        append("${it.date} ${it.timeStart} - ${it.timeEnd}")
    }
}

@Composable
private fun TimeRangeChooser(
    state: MutableState<BookingStateUI>,
    times: List<List<Pair<String, String>>>
) {
    for (list in times) {
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            for ((timeStart, timeEnd) in list) {
                GExaText(
                    text = "$timeStart - $timeEnd",
                    fontSize = 12.sp,
                    color = if (state.value.timeStart == timeStart && state.value.timeEnd == timeEnd) {
                        colorResource(id = R.color.white)
                    } else {
                        colorResource(id = R.color.soft_black)
                    },
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                        .padding(2.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable(
                            onClick = {
                                state.value = state.value.copy(
                                    timeStart = timeStart,
                                    timeEnd = timeEnd
                                )
                            },
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = true, color = Color.Gray)
                        )
                        .background(
                            color = if (state.value.timeStart == timeStart && state.value.timeEnd == timeEnd) {
                                colorResource(id = R.color.red)
                            } else {
                                colorResource(id = R.color.light_gray)
                            }
                        )
                        .padding(vertical = 15.dp)
                )
            }
        }
    }
}

@Composable
private fun DateTimeChooser(
    state: MutableState<BookingStateUI>,
    date: MutableState<String>,
    dateMenuIsOpen: MutableState<Boolean>,
    time: MutableState<String>,
    timeMenuIsOpen: MutableState<Boolean>,
    daysToBooking: List<String>,
    timesRangesToBooking: List<String>,
    getTimes: (String, String) -> List<List<Pair<String, String>>>,
) {
    val times = getTimes(date.value, time.value)

    Box {
        Column {
            GExaText(text = stringResource(R.string.choose_date), fontSize = 13.sp)
            Spacer(modifier = Modifier.height(10.dp))

            ChoseMenu(date.value, R.drawable.baseline_calendar_month_24, dateMenuIsOpen)
            Spacer(modifier = Modifier.height(10.dp))

            ChoseMenu(
                time.value,
                R.drawable.baseline_access_time_24,
                timeMenuIsOpen
            )

            TimeRangeChooser(state, times)
        }

        Row {
            MenuItems(dateMenuIsOpen, daysToBooking, date)
            MenuItems(timeMenuIsOpen, timesRangesToBooking, time)
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
private fun MenuItems(
    isOpen: MutableState<Boolean>,
    dataToBooking: List<String>,
    visibleValue: MutableState<String>
) {
    if (isOpen.value) {
        Column(
            modifier = Modifier
                .border(1.dp, colorResource(id = R.color.soft_gray), RoundedCornerShape(5.dp))
                .width(100.dp)
                .background(
                    color = colorResource(id = R.color.soft_white),
                    shape = RoundedCornerShape(5.dp)
                )
        ) {
            Items(dataToBooking, isOpen, visibleValue)
        }
    }
}

@Composable
private fun Items(
    dataToBooking: List<String>,
    isOpen: MutableState<Boolean>,
    visibleValue: MutableState<String>
) {
    for (booking in dataToBooking) {
        ChooseItem(
            text = booking,
            onClick = {
                isOpen.value = false
                visibleValue.value = booking
            }
        )
    }
}

@Composable
private fun ChooseItem(
    text: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true, color = Color.Gray)
            )
            .padding(5.dp)
    ) {
        GExaText(text = text, fontSize = 16.sp)
    }
}

@Composable
private fun ChoseMenu(text: String, iconId: Int, chooserIsOpen: MutableState<Boolean>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.light_gray),
                shape = RoundedCornerShape(5.dp)
            )
            .clickable(
                onClick = { chooserIsOpen.value = !chooserIsOpen.value },
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true, color = Color.Gray)
            )
            .padding(5.dp)
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = iconId),
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(10.dp))
        GExaText(text = text, fontSize = 13.sp)

        val imageVector = if (chooserIsOpen.value) {
            ImageVector.vectorResource(id = R.drawable.baseline_keyboard_arrow_up_24)
        } else {
            ImageVector.vectorResource(id = R.drawable.baseline_keyboard_arrow_down_24)
        }

        Image(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            alignment = Alignment.CenterEnd
        )
    }
}