package com.openclassroom.joiefull.data.service.network.interfaces
import com.openclassroom.joiefull.model.Product
import retrofit2.http.GET

/**
 * Joiefull network service interface with all the network methods.
 */
interface JoiefullNetworkService {
    /**
     * Method to get all Clothe products
     * @return a list of product objects.
     */
    @GET("clothes.json")
    suspend fun getClothes(): List<Product>
}