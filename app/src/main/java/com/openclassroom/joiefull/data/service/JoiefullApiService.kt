package com.openclassroom.joiefull.data.service

import com.openclassroom.joiefull.data.service.network.interfaces.JoiefullNetworkService
import com.openclassroom.joiefull.model.Product
import javax.inject.Inject

/**
 * The app API service to communicate with the server.
 */
class JoiefullApiService @Inject constructor(private val joiefullNetworkService: JoiefullNetworkService) {

    /**
     * Methode to get the clothes list.
     *
     * @return  a clothes product objects list.
     */
    suspend fun getClothes(): List<Product> {
        return joiefullNetworkService.getClothes()
    }

}