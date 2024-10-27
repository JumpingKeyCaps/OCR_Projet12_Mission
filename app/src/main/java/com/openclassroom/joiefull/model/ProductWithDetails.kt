package com.openclassroom.joiefull.model

/**
 * The data class of the product with details.
 * @param id The id of the product.
 * @param product The product.
 * @param details The product details.
 */
data class ProductWithDetails (
    val id: Int = -1,
    val product: Product = Product(),
    var details: ProductDetails = ProductDetails(id = -1)
)