package com.openclassroom.joiefull.compositions.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.openclassroom.joiefull.compositions.components.TitleSection
import com.openclassroom.joiefull.model.Product
import com.openclassroom.joiefull.model.ProductDetails

/**
 * The composable function of the screen composition responsible to display a list of products.
 *
 * @param products The list of products to display.
 * @param productsDetails The list of product details to display.
 * @param titleSection The title of the section.
 * @param modifier The modifier.
 * @param onProductClick The function to call when a product is clicked.
 * @param onLikeButtonClick The function to call when a like button is clicked.
 */

@Composable
fun ProductList(
    products: List<Product>,
    productsDetails: List<ProductDetails?>,
    titleSection: String,
    modifier: Modifier = Modifier,
    onProductClick: (Int) -> Unit,
    onLikeButtonClick: (ProductDetails) -> Unit = {}){
    Column(modifier = modifier.padding(0.dp,0.dp,0.dp,0.dp).fillMaxSize()){
        //TITLE SECTION
        TitleSection(
            title = titleSection,
            modifier = Modifier.padding(15.dp,5.dp,5.dp,0.dp)
        )
        //LIST OF PRODUCT CARDS
        LazyRow(modifier = modifier.fillMaxWidth()) {
            items(products, key = { product -> product.id }) { product ->    //(use of Key to recomposition optimisation)
                ProductCard(
                    product = product,
                    productDetails = productsDetails.find { it?.id == product.id } ?: ProductDetails(),
                    modifier = Modifier.padding(
                        start = if(products.indexOf(product) == 0) 15.dp else 3.dp, //special left padding for the 1st element of the list
                        top = 0.dp,
                        end = if(products.indexOf(product) == products.lastIndex) 15.dp else 3.dp,  //special right padding for the last element of the list
                        bottom = 0.dp),
                    pictureModifier = Modifier
                        .clickable {
                             onProductClick(product.id)
                        },
                    onLikeButtonClick = onLikeButtonClick
                )
            }
        }
    }
}