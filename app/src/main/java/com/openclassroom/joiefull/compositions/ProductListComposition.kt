package com.openclassroom.joiefull.compositions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openclassroom.joiefull.compositions.Composables.TitleSection
import com.openclassroom.joiefull.model.Product

/**
 * Composition for a list of products
 *
 * @param products List of products to display
 * @param titleSection Title of the section
 * @param modifier Modifier for the composable
 */
@Composable
fun ProductList(products: List<Product>,titleSection: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(0.dp,0.dp,0.dp,0.dp)){
        //TITLE SECTION
        TitleSection(title = titleSection, modifier = Modifier.padding(15.dp,5.dp,5.dp,0.dp))
        //LIST OF PRODUCT CARDS
        LazyRow(modifier = modifier) {
            items(products, key = { product -> product.id }) { product ->    //(use of Key to recomposition optimisation)
                ProductCard(
                    title = product.name,
                    price = product.price,
                    oldPrice = product.originalPrice,
                    likes = product.likes,
                    rating = product.rating,
                    imageUrl = product.imageUrl,
                    modifier = Modifier.padding(
                        if(products.indexOf(product) == 0) 15.dp else 3.dp, //special left padding for the 1st element of the list
                        0.dp,
                        if(products.indexOf(product) == products.lastIndex) 15.dp else 3.dp,  //special right padding for the last element of the list
                        0.dp)
                )
            }
        }
    }
}

/**
 * Preview of the ProductList composable
 */
@Preview(showSystemUi = true)
@Composable
fun ProductListPreview(){

    val sampleProducts = listOf(
        Product(id = 1,name = "Sac à main orange",price = "19.09€",originalPrice = "24.99€",likes = 12,rating = 4.5f, imageDescription = "", category = "Accessories",imageUrl = "https://raw.githubusercontent.com/OpenClassrooms-Student-Center/D-velopper-une-interface-accessible-en-Jetpack-Compose/main/img/accessories/1.jpg"),
        Product(id = 2,name = "Jean pour femme",price = "32.29€",originalPrice = "34.99€",likes = 25,rating = 2.7f,imageDescription = "", category = "Accessories",imageUrl = "https://raw.githubusercontent.com/OpenClassrooms-Student-Center/D-velopper-une-interface-accessible-en-Jetpack-Compose/main/img/bottoms/1.jpg"),
        Product(id = 3,name = "Bottes noires pour l'automne",price = "54.49€",originalPrice = "74.99€",likes = 67,rating = 3.9f,imageDescription = "", category = "Accessories",imageUrl = "https://raw.githubusercontent.com/OpenClassrooms-Student-Center/D-velopper-une-interface-accessible-en-Jetpack-Compose/main/img/shoes/1.jpg"),
        Product(id = 4,name = "Blazer marron",price = "9.99€",originalPrice = "29.99€",likes = 89,rating = 4.8f,imageDescription = "", category = "Accessories",imageUrl = "https://raw.githubusercontent.com/OpenClassrooms-Student-Center/D-velopper-une-interface-accessible-en-Jetpack-Compose/main/img/tops/1.jpg"),
        Product(id = 5,name = "Pull vert femme",price = "27.59€",originalPrice = "39.99€",likes = 2,rating = 3.1f,imageDescription = "", category = "Accessories",imageUrl = "https://raw.githubusercontent.com/OpenClassrooms-Student-Center/D-velopper-une-interface-accessible-en-Jetpack-Compose/main/img/tops/2.jpg"),
    )


    ProductList(products = sampleProducts, titleSection = "Bas")

}