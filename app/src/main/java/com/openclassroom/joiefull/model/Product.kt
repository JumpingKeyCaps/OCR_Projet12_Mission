package com.openclassroom.joiefull.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int = 0,
    val picture: Picture,
    val name: String,
    val category: String,
    val likes: Int,
    val price: Float,
    @Json(name = "original_price")val originalPrice: Float,
    val commentary: String? = null,
    val rating: Float? = null,
    val description: String? = null,
    val isLiked: Boolean = false,

) : Parcelable {

    @Parcelize
    data class Picture(
        val url: String,
        val description: String
    ) : Parcelable
}