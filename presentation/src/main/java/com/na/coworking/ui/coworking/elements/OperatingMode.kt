package com.na.coworking.ui.coworking.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.na.coworking.R
import com.na.coworking.domain.entities.Workspace
import com.na.coworking.ui.global.GExaText
import com.na.coworking.ui.global.GTeraText

@Composable
fun OperatingMode(coworking: MutableState<Workspace>) {
    val weekDays = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")

    val coworkingWeekDays = coworking.value.operationMode.map { it.weekDayNumber }

    Column(
        modifier = Modifier.padding(horizontal = 30.dp)
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Title()

        for (day in weekDays.indices) {
            Spacer(modifier = Modifier.height(5.dp))
            WeekDayCard(day, coworking, coworkingWeekDays, weekDays)
        }
    }
}

@Composable
private fun Title() {
    GTeraText(text = "Режим работы", fontSize = 16.sp)
}

@Composable
private fun WeekDayCard(
    day: Int,
    coworking: MutableState<Workspace>,
    coworkingWeekDays: List<Int>,
    weekDays: List<String>
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (day + 1 in coworkingWeekDays) {
            NotWeekend(day, coworking, weekDays)
        } else {
            Weekend()
        }
    }
}

@Composable
private fun Weekend() {
    Box(
        modifier = Modifier
            .shadow(2.dp, CircleShape)
            .border(
                3.dp,
                color = colorResource(R.color.soft_gray),
                shape = CircleShape
            )
            .size(40.dp)
    )

    Spacer(modifier = Modifier.width(10.dp))

    GExaText(text = stringResource(R.string.weekend), fontSize = 13.sp)
}

@Composable
private fun NotWeekend(
    day: Int,
    coworking: MutableState<Workspace>,
    weekDays: List<String>
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .shadow(2.dp, CircleShape)
            .background(
                color = colorResource(R.color.red),
                shape = CircleShape
            )
            .size(40.dp)
    ) {
        GExaText(
            text = weekDays[day],
            fontSize = 16.sp,
            color = colorResource(id = R.color.white)
        )
    }

    Spacer(modifier = Modifier.width(10.dp))

    val opMode = coworking.value.operationMode.first { it.weekDayNumber == day + 1 }

    GExaText(text = "${opMode.timeStart} - ${opMode.timeEnd}", fontSize = 13.sp)
}