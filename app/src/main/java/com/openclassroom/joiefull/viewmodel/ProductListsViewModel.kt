package com.openclassroom.joiefull.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassroom.joiefull.data.repository.ProductDetailsRepository
import com.openclassroom.joiefull.data.repository.ProductRepository
import com.openclassroom.joiefull.data.service.network.NetworkException
import com.openclassroom.joiefull.model.ProductDetails
import com.openclassroom.joiefull.model.ProductWithDetails
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

    private val _productsWithDetails = MutableStateFlow<Map<String, List<ProductWithDetails>>>(emptyMap())
    val productsWithDetails: StateFlow<Map<String, List<ProductWithDetails>>> get() = _productsWithDetails

    /** Initialisation du ViewModel */
    init { fetchProductsWithDetails() }

    /**
     * Method to fetch the products with details from the API/DBB.
     */
    private fun fetchProductsWithDetails(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Fetch product list from the API
                val products = productRepository.getClothes()
                // Fetch product details from the database
                viewModelScope.launch(Dispatchers.IO) {
                    val productDetailsFlow = productDetailsRepository.getAllProductDetails().conflate()
                    productDetailsFlow.collect { productDetailsDtoList ->
                        val productDetailsList = productDetailsDtoList.map { productDetailsDto ->
                            productDetailsDto.let { ProductDetails.fromDto(it) }
                        }
                        // Build a list of ProductWithDetails objects
                        val productWithDetailsBuild = products.map { product ->
                            ProductWithDetails(product.id, product, productDetailsList.find { it.id == product.id } ?: ProductDetails(product.id))
                        }
                        // Group the ProductWithDetails objects by category and update the state
                        _productsWithDetails.value = productWithDetailsBuild.groupBy { it.product.category }
                    }
                }
            } catch (e: Exception) {
                // Handle network errors, server errors, etc.
                when (e) {
                    is NetworkException.ServerErrorException -> { }
                    is NetworkException.NetworkConnectionException -> { }
                    is NetworkException.UnknownNetworkException -> { }
                    else -> { } // other case of error
                }
            }
        }
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