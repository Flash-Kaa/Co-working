package com.na.coworking.domain.usecases.bookings

import com.na.coworking.domain.entities.CoworkingBooking
import com.na.coworking.domain.entities.Workspace
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GetFreeTimesUseCase {
    private val dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    operator fun invoke(
        date: String,
        time: String,
        coworking: Workspace,
        bookings: List<CoworkingBooking>
    ): List<List<Pair<String, String>>> {
        val coworkingTime = coworking.operationMode.firstOrNull {
            it.weekDayNumber == LocalDate.parse(date, dtFormatter).dayOfWeek.value
        }

        if (coworkingTime == null) return emptyList()

        val chunk = if (time.contains("час")) {
            time.take(2).trim().toInt() * 60
        } else {
            time.take(2).trim().toInt()
        }

        var startMinutes = minuteCount(coworkingTime.timeStart)
        val endMinutes = minuteCount(coworkingTime.timeEnd)
        val curDayBookings = bookings.filter { date == it.date }

        val list = mutableListOf<Pair<String, String>>()
        while (startMinutes + chunk <= endMinutes) {
            val end = startMinutes + chunk
            val cnt = curDayBookings.count {
                minuteCount(it.timeStart) in (startMinutes..end)
                        || minuteCount(it.timeEnd) in (startMinutes..end)
            }

            if (coworking.objects.size != cnt) {
                list.add(minuteCountToTime(startMinutes) to minuteCountToTime(end))
            }

            startMinutes += chunk
        }

        return list.chunked(3)
    }

    private fun minuteCount(str: String): Int {
        val split = str.split(":").map(String::toInt)
        return split[0] * 60 + split[1]
    }

    private fun minuteCountToTime(count: Int): String {
        val minutes = count % 60

        return "${count / 60}:${if (minutes < 10) "0$minutes" else minutes}"
    }
}