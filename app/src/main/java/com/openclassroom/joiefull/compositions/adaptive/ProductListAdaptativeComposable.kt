package com.openclassroom.joiefull.compositions.adaptive

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.openclassroom.joiefull.compositions.composables.ProductCard
import com.openclassroom.joiefull.compositions.composables.TitleSection
import com.openclassroom.joiefull.model.Product

/**
 * The composable function of the adaptive screen composition responsible to display a list of products.
 * @param products The list of products to display.
 * @param titleSection The title of the section.
 * @param modifier The modifier.
 * @param onProductClick The function to call when a product is clicked.
 */

@Composable
fun ProductListAdaptive(products: List<Product>,
                        titleSection: String,
                        modifier: Modifier = Modifier,
                        onProductClick: (Product) -> Unit) {
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
                        //update the shared product
                        onProductClick(product)
                    }
                )
            }
        }
    }
}