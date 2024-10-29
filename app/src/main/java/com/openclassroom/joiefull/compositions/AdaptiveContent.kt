package com.openclassroom.joiefull.compositions

import android.content.Intent
import android.net.Uri
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import com.openclassroom.joiefull.R
import com.openclassroom.joiefull.compositions.screens.DetailsScreen
import com.openclassroom.joiefull.compositions.screens.ProductsScreen
import com.openclassroom.joiefull.di.AppConfig.PAN_DIVIDER_RATIO

/**
 * Adaptive composition of the application.
 *
 * @param navigator The adaptive navigator.
 * @param deepLink The deep link  URI from the share feature option
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AdaptiveContent(
    navigator: ThreePaneScaffoldNavigator<Int>,
    deepLink: Uri? = null){

    //Share deep link : auto-open details pan with the given shared product id
    LaunchedEffect(deepLink) {
        deepLink?.lastPathSegment?.toIntOrNull()?.let {
            navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, it)
        }
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
                    }
                )
            }
        },
        //2- Details pane content --- (product details screen)
        detailPane  = {
            val context = LocalContext.current
            // Create a shareable content (deeplink + product name) for the share feature
            val sharedUriLink = stringResource(R.string.share_feature_deeplink_builder)
            val sharedBody = stringResource(R.string.share_feature_share_text_builder)

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
                        isExpandedMode = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED,
                        shareFeatureClick = { id,name ->
                            val shareIntent = Intent(Intent.ACTION_SEND)
                            shareIntent.type = "text/plain"
                            // build a shareable content (deeplink + product name) for the share feature
                            val sharedContent = sharedBody.format(name,sharedUriLink.format(id.toString()))
                            shareIntent.putExtra(Intent.EXTRA_TEXT, sharedContent)
                            val chooser = Intent.createChooser(shareIntent, "Partager cet article via")
                            context.startActivity(chooser)
                        }
                    )
                }
            }
        }
    )
}



