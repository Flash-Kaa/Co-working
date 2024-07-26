package com.na.coworking.domain.usecases.bookings

import com.na.coworking.domain.entities.CoworkingBooking
import com.na.coworking.domain.entities.Workspace
import com.na.coworking.domain.entities.WorkspaceObject
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GetTemplatesUseCase {
    private val dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    operator fun invoke(
        timeStart: String,
        timeEnd: String,
        date: String,
        coworking: Workspace,
        bookings: List<CoworkingBooking>
    ): List<WorkspaceObject> {
        val coworkingTime = coworking.operationMode.firstOrNull {
            it.weekDayNumber == LocalDate.parse(date, dtFormatter).dayOfWeek.value
        }

        if (coworkingTime == null) return emptyList()

        val start = minuteCount(timeStart)
        val end = minuteCount(timeEnd)

        val bookingsObjId = bookings
            .filter { it.date == date }
            .filter { minuteCount(it.timeStart) in (start..end) || minuteCount(it.timeEnd) in (start..end) }
            .map { it.idObject }

        return coworking.objects.map { obj ->
            if (bookingsObjId.any { it == obj.id }) obj.copy(
                isEnableToChosen = false
            ) else obj
        }
    }

    private fun minuteCount(str: String): Int {
        val split = str.split(":").map(String::toInt)
        return split[0] * 60 + split[1]
    }
}