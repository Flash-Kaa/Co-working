package com.na.coworking.data.network.entities


import com.google.gson.annotations.SerializedName
import com.na.coworking.domain.entities.WorkspaceObject

class TemplatesObjectsNetEntity :
    ArrayList<TemplatesObjectsNetEntity.TemplatesObjectsNetEntityItem>() {
    data class TemplatesObjectsNetEntityItem(
        @SerializedName("category")
        val category: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("image")
        val image: Image,
        @SerializedName("isReservable")
        val isReservable: Boolean
    ) {
        data class Image(
            @SerializedName("url")
            val url: String
        )

        fun toObjTemplate() = WorkspaceObject.Template(
            id = id,
            category = category,
            image = image.url,
            isReservable = isReservable
        )
    }
}