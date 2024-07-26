package com.na.coworking.ui.coworking

import com.na.coworking.domain.entities.WorkspaceObject
import java.time.format.DateTimeFormatter

private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

data class BookingStateUI(
    val timeStart: String = "",
    val timeEnd: String = "",
    val workspaceObject: WorkspaceObject? = null,
    val date: String = ""
)
