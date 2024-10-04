package com.openclassroom.joiefull.compositions

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import com.openclassroom.joiefull.compositions.Composables.TitleSection
import com.openclassroom.joiefull.model.Product

@Composable
fun MainComposition(products: List<Product>){

    // Get the current window size class to detect screen size (phone or tablet)
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    //Check the width of the screen
    when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            // UI for small screens
            Log.d("ScreenMode", "---> COMPACT ") //Phone
        }
        WindowWidthSizeClass.MEDIUM -> {
            // UI for medium-sized screens
            Log.d("ScreenMode", "---> MEDIUM ") // mixed
        }
        WindowWidthSizeClass.EXPANDED -> {
            // UI for large screens
            Log.d("ScreenMode", "---> EXPANDED ") // tablet
        }
    }


    // @#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@

    Scaffold(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Column(modifier = Modifier.padding(it)) {


            // Product lists composable
            ProductList(products = products,titleSection = "Hauts")




        }
    }

    // @#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@#@

}

@Preview(showSystemUi = true)
@PreviewLightDark
@PreviewScreenSizes
@Composable
fun MainCompositionPreview() {
    val sampleProducts = listOf(
        Product(id = 1,name = "Sac à main orange",price = "19.09€",originalPrice = "24.99€",likes = 12,rating = 4.5f, imageDescription = "", category = "Accessories",imageUrl = "https://raw.githubusercontent.com/OpenClassrooms-Student-Center/D-velopper-une-interface-accessible-en-Jetpack-Compose/main/img/accessories/1.jpg"),
        Product(id = 2,name = "Jean pour femme",price = "32.29€",originalPrice = "34.99€",likes = 25,rating = 2.7f,imageDescription = "", category = "Accessories",imageUrl = "https://raw.githubusercontent.com/OpenClassrooms-Student-Center/D-velopper-une-interface-accessible-en-Jetpack-Compose/main/img/bottoms/1.jpg"),
        Product(id = 3,name = "Bottes noires pour l'automne",price = "54.49€",originalPrice = "74.99€",likes = 67,rating = 3.9f,imageDescription = "", category = "Accessories",imageUrl = "https://raw.githubusercontent.com/OpenClassrooms-Student-Center/D-velopper-une-interface-accessible-en-Jetpack-Compose/main/img/shoes/1.jpg"),
        Product(id = 4,name = "Blazer marron",price = "9.99€",originalPrice = "29.99€",likes = 89,rating = 4.8f,imageDescription = "", category = "Accessories",imageUrl = "https://raw.githubusercontent.com/OpenClassrooms-Student-Center/D-velopper-une-interface-accessible-en-Jetpack-Compose/main/img/tops/1.jpg"),
        Product(id = 5,name = "Pull vert femme",price = "27.59€",originalPrice = "39.99€",likes = 2,rating = 3.1f,imageDescription = "", category = "Accessories",imageUrl = "https://raw.githubusercontent.com/OpenClassrooms-Student-Center/D-velopper-une-interface-accessible-en-Jetpack-Compose/main/img/tops/2.jpg"),
    )
    MainComposition(sampleProducts)
}