package com.openclassroom.joiefull.compositions.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.openclassroom.joiefull.compositions.adaptive.ProductListAdaptive
import com.openclassroom.joiefull.model.Product
import com.openclassroom.joiefull.viewmodel.ProductListsViewModel


/**
 * The composable function of the screen composition responsible to display all the product lists.
 * @param viewModel The view model. (Hilt injected case !)
 * @param sharedProduct The shared product.
 * @param navigator The navigator.
 */

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ProductsScreenAdaptive(
    navigator: ThreePaneScaffoldNavigator<Nothing>,
    viewModel: ProductListsViewModel = hiltViewModel(),
    sharedProduct: MutableState<Product?>){

    //Get the list of products by category from viewmodel
    val productsByCategory by viewModel.products.collectAsStateWithLifecycle()

    Scaffold(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(0.dp)) { it->
        LazyColumn(modifier = Modifier.padding(it)) {
            items(productsByCategory.toList(),key = { (categoryName, _) -> categoryName }) { (categoryName, categoryProducts) ->
                ProductListAdaptive(products = categoryProducts,
                    titleSection = categoryName,
                    onProductClick = {
                        //update the shared product with the clicked product object
                        sharedProduct.value = it
                        //navigate to the secondary panel only if it is hidden
                        if (navigator.scaffoldValue[SupportingPaneScaffoldRole.Supporting] == PaneAdaptedValue.Hidden) {
                            navigator.navigateTo(ThreePaneScaffoldRole.Secondary)
                        }
                    }
                )
            }
        }
    }

}
