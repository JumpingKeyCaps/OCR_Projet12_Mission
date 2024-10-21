package com.openclassroom.joiefull.compositions.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.openclassroom.joiefull.compositions.composables.ProductList
import com.openclassroom.joiefull.viewmodel.ProductListsViewModel


/**
 * The composable function of the screen composition to display all the product lists.
 *
 * @param viewModel The view model. (Hilt injected case !)
 * @param onProductClick The function to call when a product is clicked.
 */

@Composable
fun ProductsScreen(
    viewModel: ProductListsViewModel = hiltViewModel(),
    onProductClick: (Int) -> Unit){
    //Get the list of products by category from viewmodel
    val productsByCategory by viewModel.products.collectAsStateWithLifecycle()
    //Get the list of products details from viewmodel
    val productsDetails by viewModel.productsDetails.collectAsStateWithLifecycle()

    Scaffold(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(0.dp)) { it->
        LazyColumn(modifier = Modifier.padding(it)) {
            items(productsByCategory.toList(),key = { (categoryName, _) -> categoryName }) { (categoryName, categoryProducts) ->
                ProductList(
                    products = categoryProducts,
                    productsDetails = productsDetails,
                    titleSection = categoryName,
                    onProductClick = onProductClick,
                    onLikeButtonClick = {
                          viewModel.updateLikeValue(it,!it.isLiked)
                    }
                )
            }
        }
    }

}
