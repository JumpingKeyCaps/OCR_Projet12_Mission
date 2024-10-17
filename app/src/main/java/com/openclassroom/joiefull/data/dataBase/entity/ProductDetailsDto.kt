package com.openclassroom.joiefull.data.dataBase.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class used by Room to create the product_details DataBase table and ease the CRUD operation on it.
 * @param id : the product ID
 * @param isLiked : if the product is liked by the user
 * @param rating : the product rating by the user
 * @param commentary : the product commentary by the user
 */

@Entity(tableName = "product_details")
data class ProductDetailsDto(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "isLiked")
    var isLiked: Boolean = false,
    @ColumnInfo(name = "rating")
    var rating: Float = 0f,
    @ColumnInfo(name = "commentary")
    var commentary: String = ""
)