package com.na.coworking.data.network.entities


import com.google.gson.annotations.SerializedName
import com.na.coworking.domain.entities.Amenity
import com.na.coworking.domain.entities.Workspace
import com.na.coworking.domain.entities.WorkspaceObject

class WorkspacesNetEntity : ArrayList<WorkspacesNetEntity.WorkspaceNetEntityItem>() {
    data class WorkspaceNetEntityItem(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("address")
        val address: String?,
        @SerializedName("amenities")
        val amenities: List<Amenity>?,
        @SerializedName("idCreator")
        val idCreator: Int,
        @SerializedName("images")
        val images: List<Image>?,
        @SerializedName("institute")
        val institute: String?,
        @SerializedName("latitude")
        val latitude: Int,
        @SerializedName("longitude")
        val longitude: Int,
        @SerializedName("objects")
        val objects: List<Object>?,
        @SerializedName("operationMode")
        val operationMode: List<OperationMode>?,
        @SerializedName("privacy")
        val privacy: Int,
        @SerializedName("rating")
        val rating: Double
    ) {
        data class Amenity(
            @SerializedName("id")
            val id: Int,
            @SerializedName("idTemplate")
            val idTemplate: Int,
            @SerializedName("idWorkspace")
            val idWorkspace: Int
        ) {
            fun toAmenity(template: com.na.coworking.domain.entities.Amenity.Template) = Amenity(
                id = id,
                template = template
            )
        }

        data class Image(
            @SerializedName("id")
            val id: Int,
            @SerializedName("idOwner")
            val idOwner: Int,
            @SerializedName("typeOwner")
            val typeOwner: Int,
            @SerializedName("url")
            val url: String?
        ) {
            fun toImage() = Workspace.Image(
                id = id,
                url = url ?: "",
                idOwner = idOwner,
                typeOwner = typeOwner
            )
        }

        data class Object(
            @SerializedName("height")
            val height: Int,
            @SerializedName("id")
            val id: Int,
            @SerializedName("idTemplate")
            val idTemplate: Int,
            @SerializedName("idWorkspace")
            val idWorkspace: Int,
            @SerializedName("width")
            val width: Int,
            @SerializedName("x")
            val x: Int,
            @SerializedName("y")
            val y: Int
        ) {
            fun toObject(template: WorkspaceObject.Template) = WorkspaceObject(
                height = height,
                id = id,
                width = width,
                x = x,
                y = y,
                template = template
            )
        }

        data class OperationMode(
            @SerializedName("id")
            val id: Int,
            @SerializedName("idWorkspace")
            val idWorkspace: Int,
            @SerializedName("timeEnd")
            val timeEnd: String,
            @SerializedName("timeStart")
            val timeStart: String,
            @SerializedName("weekDayNumber")
            val weekDayNumber: Int
        ) {
            fun toOperationMode() = com.na.coworking.domain.entities.OperationMode(
                id = id,
                idWorkspace = idWorkspace,
                timeEnd = timeEnd,
                timeStart = timeStart,
                weekDayNumber = weekDayNumber
            )
        }

        fun toWorkspace(
            objTemplates: List<WorkspaceObject.Template>,
            amenityTemplate: List<com.na.coworking.domain.entities.Amenity.Template>
        ) = Workspace(
            id = id,
            name = name,
            description = description,
            address = address ?: "",
            institute = institute ?: "",
            rating = rating,
            privacy = privacy,
            images = images?.map { it.toImage() } ?: emptyList(),
            objects = objects?.map { obj ->
                obj.toObject(
                    objTemplates.first { template ->
                        template.id == obj.idTemplate
                    }
                )
            } ?: emptyList(),
            operationMode = operationMode?.map { it.toOperationMode() } ?: emptyList(),
            amenities = amenities?.map { amen ->
                amen.toAmenity(
                    amenityTemplate.first { template ->
                        template.id == amen.idTemplate
                    }
                )
            } ?: emptyList()
        )
    }
}