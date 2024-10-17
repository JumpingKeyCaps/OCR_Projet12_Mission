package com.openclassroom.joiefull.compositions.composables

import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.navArgument
import androidx.window.core.layout.WindowWidthSizeClass
import com.openclassroom.joiefull.model.Product


/**
 * Composition for a list of products
 *
 * @param products List of products to display
 * @param titleSection Title of the section
 * @param modifier Modifier for the composable
 */
@Composable
fun ProductList(products: List<Product>, titleSection: String, modifier: Modifier = Modifier, navController: NavController,screenMode: MutableState<WindowWidthSizeClass>) {
    Column(modifier = modifier.padding(0.dp,0.dp,0.dp,0.dp)){
        //TITLE SECTION
        TitleSection(title = titleSection, modifier = Modifier.padding(15.dp,5.dp,5.dp,0.dp))
        //LIST OF PRODUCT CARDS
        LazyRow(modifier = modifier) {
            items(products, key = { product -> product.id }) { product ->    //(use of Key to recomposition optimisation)
                ProductCard(
                    product = product,
                    modifier = Modifier.padding(
                        if(products.indexOf(product) == 0) 15.dp else 3.dp, //special left padding for the 1st element of the list
                        0.dp,
                        if(products.indexOf(product) == products.lastIndex) 15.dp else 3.dp,  //special right padding for the last element of the list
                        0.dp),
                    pictureModifier = Modifier.clickable {

                        // navigate to details screen
                        if(screenMode.value == WindowWidthSizeClass.COMPACT || screenMode.value == WindowWidthSizeClass.MEDIUM){

                            navController.navigate("ProductDetailScreen/${product.id}")
                        }

                    }
                )
            }
        }
    }
}
