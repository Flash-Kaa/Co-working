package com.na.coworking.data.network

import com.na.coworking.data.network.entities.AccessToken
import com.na.coworking.data.network.entities.LoginDataForPost
import com.na.coworking.data.network.entities.ReservationsNetEntity
import com.na.coworking.data.network.entities.TemplatesAmenitiesNetEntity
import com.na.coworking.data.network.entities.TemplatesObjectsNetEntity
import com.na.coworking.data.network.entities.WorkspacesNetEntity
import com.na.coworking.domain.entities.Location
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    // Authorization
    @POST("oauth/token")
    suspend fun authorize(@Body body: LoginDataForPost): AccessToken

    // Bookings
    @PATCH("reservations/{id}/confirm")
    suspend fun confirmBooking(@Path("id") id: Int, @Body body: Location)

    @DELETE("reservations/{id}")
    suspend fun cancelBooking(@Query("id") id: Int)

    @GET("reservations")
    suspend fun getListOfUserBookings(@Query("idUser") userId: Int): ReservationsNetEntity

    @GET("reservations")
    suspend fun getListOfWorkspaceBookings(
        @Query("idWorkspace") workspaceId: Int
    ): ReservationsNetEntity

    @POST("reservations")
    suspend fun sendBooking(@Body body: ReservationsNetEntity.ReservationsNetEntityItem)

    // Workspaces
    @GET("workspaces")
    suspend fun getWorkspaces(@Query("isFull") isFull: Boolean = true): WorkspacesNetEntity

    @GET("workspaces/{idWorkspace}")
    suspend fun getWorkspaceById(
        @Query("idWorkspace") workspaceId: Int,
        @Query("isFull") isFull: Boolean = true
    ): WorkspacesNetEntity.WorkspaceNetEntityItem

    // Images and icons
    @GET("templates/objects")
    suspend fun getTemplatesObjects(): TemplatesObjectsNetEntity

    @GET("templates/amenities")
    suspend fun getTemplatesAmenities(): TemplatesAmenitiesNetEntity
}