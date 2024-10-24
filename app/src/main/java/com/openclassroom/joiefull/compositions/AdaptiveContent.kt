package com.openclassroom.joiefull.compositions

import androidx.activity.compose.BackHandler
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import com.openclassroom.joiefull.compositions.screens.DetailsScreen
import com.openclassroom.joiefull.compositions.screens.ProductsScreen
import com.openclassroom.joiefull.di.AppConfig.PAN_DIVIDER_RATIO

/**
 * Adaptive composition of the application.
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AdaptiveContent(){

    //Create a navigator
    val navigator = rememberListDetailPaneScaffoldNavigator<Int>()
    //Manage back navigation
    BackHandler(enabled = navigator.canNavigateBack()) {
        navigator.navigateBack()
    }
    //Create the adaptive Scaffold
    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        //1- Main pane content --- (list of products screen)
        listPane   = {
            AnimatedPane(modifier = Modifier
                .preferredWidth((currentWindowSize().width * PAN_DIVIDER_RATIO).dp)) { //60% of the screen width
                ProductsScreen(
                    onProductClick = {
                        navigator.navigateTo(ListDetailPaneScaffoldRole.Detail,it)
                })
            }
        },
        //2- Details pane content --- (product details screen)
        detailPane  = {
            val productId = navigator.currentDestination?.content
            if (productId != null) {
                AnimatedPane(modifier = Modifier
                    .preferredWidth((currentWindowSize().width * (1-PAN_DIVIDER_RATIO)).dp)//30% of the screen width
                ) {
                    DetailsScreen(
                        productId = productId,
                        onBackClick = {
                            navigator.navigateBack()
                        },
                        isExpandedMode = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED
                    )
                }
            }
        }
    )
}


/**
 * Preview of the adaptive composition.
 */
@Preview(showBackground = true)
@Composable
fun AdaptiveContentPreview() {
    AdaptiveContent()
}