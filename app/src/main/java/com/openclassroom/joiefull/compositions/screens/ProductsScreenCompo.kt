package com.openclassroom.joiefull.compositions.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.window.core.layout.WindowWidthSizeClass
import com.openclassroom.joiefull.compositions.composables.ProductList
import com.openclassroom.joiefull.model.Product
import com.openclassroom.joiefull.viewmodel.ProductListsViewModel


/**
 * The composable function of the screen composition responsible to display all the product lists.
 * @param navController The navigation controller.
 * @param viewModel The view model. (Hilt injected case !)
 */

@Composable
fun ProductsScreen(navController: NavController,screenMode: MutableState<WindowWidthSizeClass>, viewModel: ProductListsViewModel = hiltViewModel()){

    val productsByCategory by viewModel.products.collectAsStateWithLifecycle()



    Scaffold(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(0.dp)) { it->
        LazyColumn(modifier = Modifier.padding(it)) {
            items(productsByCategory.toList(),key = { (categoryName, _) -> categoryName }) { (categoryName, categoryProducts) ->
                ProductList(products = categoryProducts, titleSection = categoryName,navController = navController,screenMode = screenMode)


            }
        }
    }






}
