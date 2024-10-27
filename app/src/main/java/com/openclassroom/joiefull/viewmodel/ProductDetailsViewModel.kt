package com.openclassroom.joiefull.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassroom.joiefull.data.repository.ProductDetailsRepository
import com.openclassroom.joiefull.data.repository.ProductRepository
import com.openclassroom.joiefull.data.service.network.NetworkException
import com.openclassroom.joiefull.model.Product
import com.openclassroom.joiefull.model.ProductDetails
import com.openclassroom.joiefull.model.ProductWithDetails
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.launch

/**
 * The ViewModel of the screen composition to display the product details.
 *
 * @param productId The id of the product to display.
 * @param productDetailsRepository The repository of the product details.
 * @param productRepository The repository of the product.
 */
@HiltViewModel(assistedFactory = ProductDetailsViewModel.Factory::class)
class ProductDetailsViewModel @AssistedInject constructor(
    @Assisted productId: Int,
    private val productDetailsRepository: ProductDetailsRepository,
    private val productRepository: ProductRepository
): ViewModel(){

    /**
     * Factory interface for creating instances of ProductDetailsViewModel with a given Product Id.
     */
    @AssistedFactory
    interface Factory {
        fun create(productId: Int): ProductDetailsViewModel
    }

    /** the product id to display **/
    private val id = productId

    /**
     * Initialisation du ViewModel
     */
    init { loadData() }

    /**
     * Method to load the data via the product Id
     */
    private fun loadData() {
        if (id == -1) { throw IllegalArgumentException("Id cannot be -1") }
        fetchProductsWithDetailsById(id)
    }


    private val _productWithDetails = MutableStateFlow(ProductWithDetails())
    val productWithDetails: StateFlow<ProductWithDetails> get() = _productWithDetails


    /**
     * Method to fetch the product with details from the API/DBB with a specific ID
     *
     * @param id the id of the product to fetch.
     */
    private fun fetchProductsWithDetailsById(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val responseApi = productRepository.getClothes()
                val productWithThisId = responseApi.find { it.id == id } ?: Product()
                viewModelScope.launch(Dispatchers.IO) {
                    //Get the product details from the database
                    val productDetailsFlow = productDetailsRepository.getProductDetailsById(id).conflate()
                    //Collect the product details response flow from the database
                    productDetailsFlow.collect { productDetailsDto ->
                        //Check the response to know if the product had an details entry in the database
                        if(productDetailsDto != null){
                            //the product exist in the details database, update the product details object to emit
                            val productDetails = productDetailsDto.let { ProductDetails.fromDto(it) }
                            _productWithDetails.value = ProductWithDetails(id, productWithThisId, productDetails)
                        }else{
                            //the product do not exist, add a new default entry for this product details in the database
                            addProductDetails(ProductDetails(id = id))
                        }
                    }
                }
            } catch (e: Exception) {
                // Handle network errors, server errors, etc.
                when (e) {
                    is NetworkException.ServerErrorException -> {}
                    is NetworkException.NetworkConnectionException -> {}
                    is NetworkException.UnknownNetworkException -> {}
                    else -> {} // other case of error
                }
            }
        }
    }

    /**
     * Method to add a product details to the database.
     * @param productDetails the product details to add.
     */
    fun addProductDetails(productDetails: ProductDetails){
        viewModelScope.launch(Dispatchers.IO) {
            productDetailsRepository.addProductDetails(productDetails)
        }
    }

    /**
     * Method to update the like value of a product.
     * @param isLiked the new like value.
     */
    fun updateLikeValue(isLiked: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
           productDetailsRepository.addProductDetails(_productWithDetails.value.details.copy(isLiked = isLiked))
        }
    }



}