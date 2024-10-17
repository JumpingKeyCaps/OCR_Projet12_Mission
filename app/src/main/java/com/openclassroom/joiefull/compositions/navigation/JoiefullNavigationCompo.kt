package com.openclassroom.joiefull.compositions.navigation

import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.window.core.layout.WindowWidthSizeClass
import com.openclassroom.joiefull.compositions.screens.ProductDetailsScreen
import com.openclassroom.joiefull.compositions.screens.ProductsScreen
import com.openclassroom.joiefull.model.Product

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

        composable(NavRoutes.PRODUCTS_SCREEN,
            enterTransition = { return@composable fadeIn(tween(1000))},
            exitTransition = {  return@composable slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(1000)
            )},
            popEnterTransition = { return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(1000)
            )}
        ){

            //Check the width of the screen
            when (screenMode.value) {
                WindowWidthSizeClass.COMPACT,WindowWidthSizeClass.MEDIUM -> {
                    // UI for small screens
                    Log.d("ScreenMode", "---> COMPACT ") //Phone
                    ProductsScreen(navController = navController,screenMode = screenMode)
                }
                WindowWidthSizeClass.EXPANDED -> {
                    // UI for large screens
                    Log.d("ScreenMode", "---> EXPANDED ") // tablet

                    Row {
                        Box(modifier = Modifier.weight(55f)){
                            ProductsScreen(navController = navController,screenMode = screenMode)
                        }
                        Box(modifier = Modifier.weight(45f)){
                            ProductDetailsScreen(navController = navController)
                        }

                    }

                }
            }


        }


        composable(
            "ProductDetailScreen/{productId}",
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(1000)
                )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(1000)
                )
            },
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->

            val productId = backStackEntry.arguments?.getInt("productId") // on recupere notre product Id



            when (screenMode.value) {
                WindowWidthSizeClass.COMPACT,WindowWidthSizeClass.MEDIUM -> {
                    // UI for small screens
                    Log.d("ScreenMode", "---> COMPACT ") //Phone
                    ProductDetailsScreen()
                }

                WindowWidthSizeClass.EXPANDED -> {
                    // UI for large screens
                    Log.d("ScreenMode", "---> EXPANDED ") // tablet

                    Row {
                        Box(modifier = Modifier.weight(60f)){
                            ProductsScreen(navController = navController,screenMode = screenMode)
                        }
                        Box(modifier = Modifier.weight(40f)){
                            ProductDetailsScreen( navController = navController)
                        }

                    }


                }
            }



        }




    }




}