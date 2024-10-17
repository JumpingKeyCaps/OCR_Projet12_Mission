package com.openclassroom.joiefull.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassroom.joiefull.data.repository.ProductDetailsRepository
import com.openclassroom.joiefull.model.ProductDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productDetailsRepository: ProductDetailsRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(){


    private val _productDetails = MutableStateFlow(ProductDetails())
    val productDetails: StateFlow<ProductDetails> get() = _productDetails


    private val _productRating = MutableStateFlow(0f)
    val productRating: StateFlow<Float> get() = _productRating

    private val _productIsLiked = MutableStateFlow(false)
    val productIsLiked: StateFlow<Boolean> get() = _productIsLiked


    /**
     * Method to get the product details by his ID.
     * @param id the product ID.
     */
    fun getProductDetailsById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            //Get the product details from the database
            val productDetailsFlow = productDetailsRepository.getProductDetailsById(id).conflate()
            //Collect the product details response flow from the database
            productDetailsFlow.collect { productDetailsDto ->

                //Check the response to know if the product had an details entry in the database
                if(productDetailsDto == null){
                    Log.d("DTOdebug", "collect details: dto is null !")
                    //the product don't exist in the details database, we add it with default values and the ID
                    addProductDetails(productDetails = ProductDetails(id = id))

                }else{
                    Log.d("DTOdebug", "collect details: dto is not null !")
                    //the product exist in the details database, we update the product details object to emit
                         val productDetails = productDetailsDto.let { ProductDetails.fromDto(it) }
                         _productDetails.value = productDetails

                         _productRating.value = productDetails.rating
                         _productIsLiked.value = productDetails.isLiked

                }
            }

        }
    }



    /**
     * Method to add a product details to the database.
     * @param productDetails the product details to add.
     */
    fun addProductDetails(productDetails: ProductDetails){
        Log.d("UpdateProductDetails", "Update Product details : Id = ${productDetails.id} Rating = ${productDetails.rating}  isLiked = ${productDetails.isLiked}")
        viewModelScope.launch(Dispatchers.IO) {
            productDetailsRepository.addProductDetails(productDetails)
        }
    }

    fun updateLikeValue(isLiked: Boolean){
       // _productIsLiked.value = isLiked
        viewModelScope.launch(Dispatchers.IO) {
           productDetailsRepository.addProductDetails(_productDetails.value.copy(isLiked = isLiked))
        }
    }


}