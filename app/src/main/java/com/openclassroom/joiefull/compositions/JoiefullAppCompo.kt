package com.openclassroom.joiefull.compositions

import androidx.compose.runtime.Composable
import com.openclassroom.joiefull.compositions.adaptive.AdaptiveContent
import com.openclassroom.joiefull.compositions.navigation.JoiefullNavigation
import com.openclassroom.joiefull.ui.theme.JoiefullTheme

/**
 * Main composable function of the application.
 */
@Composable
fun JoiefullApp(){
    JoiefullTheme {

       // 1 ----- [CLASSIC] VERSION  (bug when : DetailsScreen(portrait)-> ProductsScreen(landscape)-> go back button)

       JoiefullNavigation()


      // 2 ----- [ADAPTIVE] VERSION (no animation transitions + span bug keyboard opening)

       // AdaptiveContent()

    }
}