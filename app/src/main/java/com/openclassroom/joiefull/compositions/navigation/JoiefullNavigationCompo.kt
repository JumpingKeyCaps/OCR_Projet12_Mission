package com.openclassroom.joiefull.compositions.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.window.core.layout.WindowWidthSizeClass
import com.openclassroom.joiefull.compositions.screens.ProductDetailsScreen
import com.openclassroom.joiefull.compositions.screens.ProductsScreen

/**
 * Navigation composable function of the application.
 */
@Composable
fun JoiefullNavigation(){
    val navController = rememberNavController()

    // Get the current window size class to detect screen size (phone or tablet)
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
    val screenMode = remember { mutableStateOf(windowSizeClass) }

    NavHost(navController,startDestination = NavRoutes.PRODUCTS_SCREEN){

        //---PRODUCTS SCREEN---
        composable("ProductsMainScreen",
            exitTransition = {  return@composable slideOutOfContainer( AnimatedContentTransitionScope.SlideDirection.Start, tween(1000))},
            popEnterTransition = { return@composable slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(1000) )}
        ){
            //Check the width of the screen
            when (screenMode.value) {
                WindowWidthSizeClass.COMPACT,WindowWidthSizeClass.MEDIUM -> { //Phone
                    ProductsScreen(onProductClick = {
                        navController.navigate("ProductDetailScreen/${it}")
                    })
                }
                WindowWidthSizeClass.EXPANDED -> { // tablet
                    Row {
                        Box(modifier = Modifier.weight(55f)){
                            ProductsScreen(onProductClick = {/** click on a product card */})
                        }
                        Box(modifier = Modifier.weight(45f)){
                            ProductDetailsScreen()
                        }
                    }
                }
            }
        }

        //---DETAILS SCREEN---
        composable(
            "ProductDetailScreen/{productId}",
            enterTransition = { return@composable slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(1000) ) },
            popExitTransition = { return@composable slideOutOfContainer( AnimatedContentTransitionScope.SlideDirection.End, tween(1000) )},
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") // get the product Id

            when (screenMode.value) {
                WindowWidthSizeClass.COMPACT,WindowWidthSizeClass.MEDIUM -> {//Phone
                    // UI for small screens
                    ProductDetailsScreen()
                }
                WindowWidthSizeClass.EXPANDED -> { // tablet
                    // UI for large screens
                    Row {
                        Box(modifier = Modifier.weight(60f)){
                            ProductsScreen(onProductClick = {/** click on a product card */})
                        }
                        Box(modifier = Modifier.weight(40f)){
                            ProductDetailsScreen()
                        }
                    }
                }
            }
        }
    }
}