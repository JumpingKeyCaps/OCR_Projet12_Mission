package com.openclassroom.joiefull.model

import com.openclassroom.joiefull.data.dataBase.entity.ProductDetailsDto

data class ProductDetails (
    val id: Int = -1,
    var isLiked: Boolean = false,
    val rating: Float = 0f,
    val commentary: String = ""
){

    fun toDto(): ProductDetailsDto {
        return ProductDetailsDto(id, isLiked, rating, commentary)
    }

    companion object {
        fun fromDto(dto: ProductDetailsDto): ProductDetails {
            return ProductDetails(dto.id, dto.isLiked, dto.rating, dto.commentary)
        }

    }

}