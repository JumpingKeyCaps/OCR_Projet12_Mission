package com.openclassroom.joiefull.data.repository

import com.openclassroom.joiefull.data.dataBase.dao.ProductDetailsDtoDao
import com.openclassroom.joiefull.data.dataBase.entity.ProductDetailsDto
import com.openclassroom.joiefull.model.ProductDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Repository for the ProductDetails data.
 */
class ProductDetailsRepository @Inject constructor(private val productDetailsDao: ProductDetailsDtoDao) {


    /**
     * Method to get the product details by his ID.
     * @param id the product ID.
     * @return a Flow of the product details.
     */
    fun getProductDetailsById(id: Int): Flow<ProductDetailsDto?> {
        return productDetailsDao.getProductDetailsById(id)
    }

    /**
     * Method to add a new product details.
     * @param productDetails the product details to add.
     */
    suspend fun addProductDetails(productDetails: ProductDetails) {
        productDetailsDao.insertProductDetails(productDetails.toDto())
    }



}