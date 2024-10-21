package com.openclassroom.joiefull.data.service.network

import com.openclassroom.joiefull.data.service.network.interfaces.JoiefullNetworkService
import com.openclassroom.joiefull.data.service.network.interfaces.RetrofitService
import com.openclassroom.joiefull.model.Product
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject

/**
 * JoiefullNetworkService implementation class.
 *
 * Redefine all the interface network methods to be use with the instance of retrofit.
 */
class JoiefullNetworkServiceImpl @Inject constructor(private val retrofitService: RetrofitService) :
    JoiefullNetworkService {

    /**
     * Methode to get the clothes list from the server using the retrofit instance.
     *
     * @return  a Product objects list.
     */
    override suspend fun getClothes(): List<Product> {
        try{
            val response = retrofitService.getClothes()
            if (response.isSuccessful) {
                return response.body()!!
            } else {
                throw NetworkException.ServerErrorException(response.code())
            }
        }catch (e: Exception){
            throw customException(e)
        }
    }

    /**
     * Methode to throw a network custom exceptions.
     *
     * @param e the generic raised exception.
     * @return  Throwable custom network exception.
     */
    private fun customException(e:Exception): Throwable{
        return when (e) {
            is SocketTimeoutException -> NetworkException.NetworkConnectionException(isSocketTimeout = true,isConnectFail = false)
            is ConnectException -> NetworkException.NetworkConnectionException(isSocketTimeout = false,isConnectFail = true)
            else -> NetworkException.UnknownNetworkException
        }
    }


}