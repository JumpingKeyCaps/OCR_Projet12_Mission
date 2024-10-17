package com.openclassroom.joiefull.compositions

import androidx.compose.runtime.Composable
import com.openclassroom.joiefull.compositions.navigation.JoiefullNavigation
import com.openclassroom.joiefull.ui.theme.JoiefullTheme

/**
 * Main composable function of the application.
 */
@Composable
fun JoiefullApp(){
    JoiefullTheme {
       JoiefullNavigation()
    }
}