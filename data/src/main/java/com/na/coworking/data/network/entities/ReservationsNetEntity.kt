package com.na.coworking.data.network.entities


import com.google.gson.annotations.SerializedName
import com.na.coworking.domain.entities.Booking
import com.na.coworking.domain.entities.CoworkingBooking
import kotlin.random.Random

class ReservationsNetEntity : ArrayList<ReservationsNetEntity.ReservationsNetEntityItem>() {
    data class ReservationsNetEntityItem(
        @SerializedName("date")
        val date: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("idObject")
        val idObject: Int,
        @SerializedName("idUser")
        val idUser: Int,
        @SerializedName("idWorkspace")
        val idWorkspace: Int,
        @SerializedName("isConfirmed")
        val isConfirmed: Boolean,
        @SerializedName("timeEnd")
        val timeEnd: String,
        @SerializedName("timeStart")
        val timeStart: String
    ) {
        companion object {
            fun CoworkingBooking.toReservation() = ReservationsNetEntityItem(
                date = date,
                idWorkspace = idWorkspace,
                idObject = idObject,
                isConfirmed = isConfirmed,
                timeEnd = timeEnd,
                timeStart = timeStart,
                idUser = idUser,
                id = Random.nextInt()
            )
        }

        fun toCoworkingBooking() = CoworkingBooking(
            date = date,
            idWorkspace = idWorkspace,
            idObject = idObject,
            timeStart = timeStart,
            timeEnd = timeEnd,
            isConfirmed = isConfirmed,
            idUser = idUser
        )

        fun toBooking(workspace: WorkspacesNetEntity.WorkspaceNetEntityItem): Booking {
            return Booking(
                id = id,
                name = workspace.name,
                timeStart = timeStart,
                timeEnd = timeEnd,
                date = date,
                isConfirmed = isConfirmed,
                image = workspace.images?.firstOrNull()?.url ?: ""
            )
        }
    }
}