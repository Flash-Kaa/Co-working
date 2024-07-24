package com.na.coworking.domain.entities

data class Amenity(
    val id: Int,
    val template: Template
) {
    data class Template(
        val id: Int,
        val name: String,
        val category: String,
        val picture: String? = null
    )
}
