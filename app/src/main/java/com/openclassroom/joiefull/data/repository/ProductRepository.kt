package com.openclassroom.joiefull.data.repository

import com.openclassroom.joiefull.data.service.JoiefullApiService
import com.openclassroom.joiefull.model.Product
import javax.inject.Inject

/**
 * Repository for the Products data.
 * Inject the used API via constructor.
 */
class ProductRepository @Inject constructor(private val joiefullApiService: JoiefullApiService) {

    /**
     * Method to get all the clothes products.
     *
     * @return a list of clothes products.
     */
    suspend fun getClothes(): List<Product> {
        //get the service response
        return joiefullApiService.getClothes()
    }

}