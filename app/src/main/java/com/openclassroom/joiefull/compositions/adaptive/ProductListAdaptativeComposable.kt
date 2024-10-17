package com.openclassroom.joiefull.compositions.adaptive

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldValue
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import com.openclassroom.joiefull.compositions.composables.ProductCard
import com.openclassroom.joiefull.compositions.composables.TitleSection
import com.openclassroom.joiefull.model.Product


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ProductListAdaptive(products: List<Product>, titleSection: String, modifier: Modifier = Modifier,navigator: ThreePaneScaffoldNavigator<Nothing>,sharedProduct: MutableState<Product?>) {
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

                        sharedProduct.value = product

                        //navigate to the secondary panel if it is hidden
                        if (navigator.scaffoldValue[SupportingPaneScaffoldRole.Supporting] == PaneAdaptedValue.Hidden) {
                            navigator.navigateTo(ThreePaneScaffoldRole.Secondary)
                        }


                        //navController.navigate("ProductDetailScreen/${product.id}") // navigate to details screen


                    }
                )
            }
        }
    }
}