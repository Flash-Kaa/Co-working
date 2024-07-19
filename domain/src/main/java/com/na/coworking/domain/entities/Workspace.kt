package com.na.coworking.domain.entities

data class Workspace(
    val id: Int,
    val name: String,
    val description: String,
    val address: String,
    val institute: String,
    val rating: Float? = null,
    val privacy: Int = 0,
    val images: List<Image> = emptyList(),
    val objects: List<WorkspaceObject> = emptyList(),
    val operationMode: List<OperationMode> = emptyList(),
    val amenities: List<Amenity> = emptyList()
) {
    data class Image(
        val id: Int,
        val url: String
    )
}