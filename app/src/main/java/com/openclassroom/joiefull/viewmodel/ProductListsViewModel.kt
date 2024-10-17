package com.openclassroom.joiefull.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassroom.joiefull.data.repository.ProductRepository
import com.openclassroom.joiefull.data.service.network.NetworkException
import com.openclassroom.joiefull.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The ViewModel of the screen composition responsible to display all the product lists.
 */
@HiltViewModel
class ProductListsViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {

    private val _products = MutableStateFlow<Map<String, List<Product>>>(emptyMap())
    val products: StateFlow<Map<String, List<Product>>> get() = _products


    /**
     * Initialisation du ViewModel
     */
    init {
        getClothes()
    }

    /**
     * Method to get the clothes list.
     */
    private fun getClothes(){
        viewModelScope.launch {
            try {
                val response = productRepository.getClothes()

                if(response!=null){
                    Log.d("GETcloth", "getClothes(): GOOD ")
                    //SUCCESS ! to fetch the list of product
                    //gen a map of products list by category
                    _products.value = groupProductsByCategory(response)

                }else{
                    //FAIL ! to find main account.
                    Log.d("GETcloth", "getClothes(): FAIL")
                }
            } catch (e: Exception) {
                Log.d("GETcloth", "getClothes(): ERROR ----> $e")

                // Handle network errors, server errors, etc.
                when (e) {
                    is NetworkException.ServerErrorException ->{}
                    is NetworkException.NetworkConnectionException  ->{
                        if(e.isSocketTimeout){}
                        if(e.isConnectFail){}
                    }
                    is NetworkException.UnknownNetworkException  ->{ }
                    else -> { } // other case of error for login
                }
            }


        }
    }




    private fun groupProductsByCategory(products: List<Product>): Map<String, List<Product>> {
        return products.groupBy { it.category }
    }




}