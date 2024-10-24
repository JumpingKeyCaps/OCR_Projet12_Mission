package com.openclassroom.joiefull.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
 * Product data class
 * - represent a product.
 * @param id the product id.
 * @param picture the product picture.
 * @param name the product name.
 * @param category the product category.
 * @param likes the product likes.
 * @param price the product price.
 * @param originalPrice the product original price.
 * @param rating the product rating.
 */
@Parcelize
data class Product(
    val id: Int = 0,
    val picture: Picture,
    val name: String,
    val category: String,
    val likes: Int,
    val price: Float,
    @Json(name = "original_price")val originalPrice: Float,
    val rating: Float? = null,
    val description: String? = null

) : Parcelable {
    @Parcelize
    data class Picture(
        val url: String,
        val description: String
    ) : Parcelable
}