package com.na.coworking.domain.entities

data class WorkspaceObject(
    val id: Int,
    val x: Int,
    val y: Int,
    val height: Int,
    val width: Int,
    val template: Template,
    val isEnableToChosen: Boolean = true
) {
    data class Template(
        val id: Int,
        val picture: String? = null,
        val category: String,
        val isReservable: Boolean
    )
}
