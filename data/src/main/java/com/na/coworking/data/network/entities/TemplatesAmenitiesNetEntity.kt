package com.na.coworking.data.network.entities


import com.google.gson.annotations.SerializedName
import com.na.coworking.domain.entities.Amenity

class TemplatesAmenitiesNetEntity :
    ArrayList<TemplatesAmenitiesNetEntity.TemplatesAmenitiesNetEntityItem>() {
    data class TemplatesAmenitiesNetEntityItem(
        @SerializedName("category")
        val category: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("image")
        val image: Image,
        @SerializedName("name")
        val name: String
    ) {
        data class Image(
            @SerializedName("url")
            val url: String
        )

        fun toAmenityTemplate() = Amenity.Template(
            id = id,
            name = name,
            category = category,
            image = image.url
        )
    }
}