package com.openclassroom.joiefull.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassroom.joiefull.data.repository.ProductDetailsRepository
import com.openclassroom.joiefull.data.repository.ProductRepository
import com.openclassroom.joiefull.data.service.network.NetworkException
import com.openclassroom.joiefull.model.Product
import com.openclassroom.joiefull.model.ProductDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The ViewModel of the screen composition responsible to display all the product lists.
 *
 * @param productRepository The repository of the product.
 * @param productDetailsRepository The repository of the product details.
 */
@HiltViewModel
class ProductListsViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val productDetailsRepository: ProductDetailsRepository
) : ViewModel() {

    private val _products = MutableStateFlow<Map<String, List<Product>>>(emptyMap())
    val products: StateFlow<Map<String, List<Product>>> get() = _products

    private val _productsDetails = MutableStateFlow<List<ProductDetails?>>(emptyList())
    val productsDetails: StateFlow<List<ProductDetails?>> get() = _productsDetails


    /**
     * Initialisation du ViewModel
     */
    init {
        fetchClothes()
        fetchProductsDetails()
    }

    /**
     * Method to get all the product details from the database.
     *
     */
    private fun fetchProductsDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            val productDetailsFlow = productDetailsRepository.getAllProductDetails().conflate()
            productDetailsFlow.collect { productDetailsDtoList ->
                val productDetailsList = productDetailsDtoList.map { productDetailsDto ->
                    productDetailsDto.let { ProductDetails.fromDto(it) }
                }
                _productsDetails.value = productDetailsList
            }
        }
    }

    /**
     * Method to get the clothes product list from the api.
     */
    private fun fetchClothes(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = productRepository.getClothes()
                _products.value = groupProductsByCategory(response)
            } catch (e: Exception) {
                when (e) {
                    is NetworkException.ServerErrorException ->{}
                    is NetworkException.NetworkConnectionException  ->{}
                    is NetworkException.UnknownNetworkException  ->{ }
                    else -> { }
                }
            }
        }
    }

    /**
     * Method to group the products by category.
     * @param products the list of products to group.
     * @return a map of the products grouped by category.
     */
    private fun groupProductsByCategory(products: List<Product>): Map<String, List<Product>> {
        return products.groupBy { it.category }
    }

    /**
     * Method to update the like value of a product.
     *
     * @param productDetails the product details to update.
     * @param isLiked the new like value.
     */
    fun updateLikeValue(productDetails: ProductDetails,isLiked: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            productDetailsRepository.addProductDetails(productDetails.copy(isLiked = isLiked))
        }
    }

}