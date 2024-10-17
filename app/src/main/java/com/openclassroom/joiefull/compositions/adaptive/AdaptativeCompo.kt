package com.openclassroom.joiefull.compositions.adaptive

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.openclassroom.joiefull.compositions.navigation.NavRoutes
import com.openclassroom.joiefull.compositions.screens.ProductDetailsScreen
import com.openclassroom.joiefull.model.Product


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AdaptiveContent(){

    //Create a navigator
    val navigator = rememberSupportingPaneScaffoldNavigator()

    //Manage back navigation
    BackHandler(enabled = navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    //Shared product to transfer between screens (use rememberSaveable to survive config change)
    val sharedProduct = rememberSaveable { mutableStateOf<Product?>(null) }



    //Create the adaptive Scaffold
    SupportingPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        //1- Main pane content ---
        mainPane  = {
            AnimatedPane(modifier = Modifier.safeContentPadding()) {

                ProductsScreenAdaptive(navigator = navigator, sharedProduct = sharedProduct)

            }
        },

        //2- Details pane content ---
        supportingPane  = {
            AnimatedPane(modifier = Modifier.safeContentPadding().preferredWidth(450.dp)) {
                ProductDetailsScreen(product = sharedProduct)
            }
        }
    )




}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AdaptiveContent()
}