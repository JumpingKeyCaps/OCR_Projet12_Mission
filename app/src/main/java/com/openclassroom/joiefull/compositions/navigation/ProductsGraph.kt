package com.openclassroom.joiefull.compositions.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.openclassroom.joiefull.compositions.screens.ProductDetailsScreen
import com.openclassroom.joiefull.compositions.screens.ProductsScreen

fun NavGraphBuilder.productsGraph(navController: NavController) {
    navigation(
        route = NavRoutes.PRODUCT_DETAIL_SCREEN,
        startDestination = NavRoutes.PRODUCTS_SCREEN
    ) {


        composable(NavRoutes.PRODUCTS_SCREEN, enterTransition = {
            return@composable fadeIn(tween(1000))
        }, exitTransition = {
            return@composable slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
            )
        }, popEnterTransition = {
            return@composable slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(700)
            )
        }) {
          //  ProductsScreen(navController = navController, sharedProduct = null)
        }


        composable(
            NavRoutes.PRODUCT_DETAIL_SCREEN,
            enterTransition = {
                return@composable slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(700)
                )
            },
            popExitTransition = {
                return@composable slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(700)
                )
            },
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") // on recupere notre product Id
            ProductDetailsScreen()
        }



    }
}