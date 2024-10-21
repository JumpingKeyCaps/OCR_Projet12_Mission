package com.openclassroom.joiefull.model

import com.openclassroom.joiefull.data.dataBase.entity.ProductDetailsDto

/**
 * Product Details data class
 * - represent all the data set by the user on a product.
 *
 * @param id the product id.
 * @param isLiked the user product like status.
 * @param rating the user product rating.
 * @param commentary the user product commentary.
 */
data class ProductDetails (
    val id: Int = -1,
    var isLiked: Boolean = false,
    val rating: Float = 0f,
    var commentary: String = ""
){
    /**
     * Method to convert the ProductDetails object to a ProductDetailsDto object.
     * @return a ProductDetailsDto object.
     */
    fun toDto(): ProductDetailsDto {
        return ProductDetailsDto(id, isLiked, rating, commentary)
    }

    companion object {
        /**
         * Method to convert the ProductDetailsDto object to a ProductDetails object.
         * @return a ProductDetails object.
         */
        fun fromDto(dto: ProductDetailsDto): ProductDetails {
            return ProductDetails(dto.id, dto.isLiked, dto.rating, dto.commentary)
        }
    }

}