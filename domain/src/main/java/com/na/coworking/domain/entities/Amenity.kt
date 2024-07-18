package com.na.coworking.domain.entities

data class Amenity(
    val id: Int,
    val template: Template
) {
    data class Template(
        val id: Int,
        val picture: String? = null,
        val name: String,
        val category: String
    )
}
