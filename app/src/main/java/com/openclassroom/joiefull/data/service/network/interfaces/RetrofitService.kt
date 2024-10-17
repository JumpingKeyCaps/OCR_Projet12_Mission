package com.openclassroom.joiefull.data.service.network.interfaces

import com.openclassroom.joiefull.model.Product
import retrofit2.Response
import retrofit2.http.GET

/**
 * The Retrofit  Service Interface to communicate with the server.
 */
interface RetrofitService {

    /**
     * Method to get all Clothes product. (GET call)
     *
     * (Use of "suspend" to allow asynchronous call inside coroutine)
     *
     * @return a list of Clothes objects.
     */
    @GET("clothes.json")
    suspend fun getClothes(): Response<List<Product>>

}