package com.openclassroom.joiefull.model

data class Product (
    val id: Int = 0,
    val name: String,
    val price: String,
    val originalPrice: String,
    val likes: Int,
    val rating: Float,
    val imageDescription: String,
    val imageUrl: String,
    val category: String
)