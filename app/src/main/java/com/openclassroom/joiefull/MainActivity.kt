package com.openclassroom.joiefull

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.openclassroom.joiefull.compositions.MainComposition
import com.openclassroom.joiefull.model.Product
import com.openclassroom.joiefull.ui.theme.JoiefullTheme

/**
 * Main activity of the application.
 */
class MainActivity : ComponentActivity() {

    /**
     * Lifecycle method that is called when the activity is created.
     * @param savedInstanceState The saved state of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //Install splash screen (keep before the call of super.onCreate())
        installSplashScreen()
        super.onCreate(savedInstanceState)
        //Edge to edge navigation mode feature
        enableEdgeToEdge()
        //TODO SPLASH SCREEN ----------
        initSplashScreenEndAnimation()



        //Product List data sample (TO REMOVE)------------
        val sampleProducts = listOf(
            Product(id = 1,name = "Sac à main orange",price = "19.09€",originalPrice = "24.99€",likes = 12,rating = 4.5f, imageDescription = "", category = "Accessories",imageUrl = "https://raw.githubusercontent.com/OpenClassrooms-Student-Center/D-velopper-une-interface-accessible-en-Jetpack-Compose/main/img/accessories/1.jpg"),
            Product(id = 2,name = "Jean pour femme",price = "32.29€",originalPrice = "34.99€",likes = 25,rating = 2.7f,imageDescription = "", category = "Accessories",imageUrl = "https://raw.githubusercontent.com/OpenClassrooms-Student-Center/D-velopper-une-interface-accessible-en-Jetpack-Compose/main/img/bottoms/1.jpg"),
            Product(id = 3,name = "Bottes noires pour l'automne",price = "54.49€",originalPrice = "74.99€",likes = 67,rating = 3.9f,imageDescription = "", category = "Accessories",imageUrl = "https://raw.githubusercontent.com/OpenClassrooms-Student-Center/D-velopper-une-interface-accessible-en-Jetpack-Compose/main/img/shoes/1.jpg"),
            Product(id = 4,name = "Blazer marron",price = "9.99€",originalPrice = "29.99€",likes = 89,rating = 4.8f,imageDescription = "", category = "Accessories",imageUrl = "https://raw.githubusercontent.com/OpenClassrooms-Student-Center/D-velopper-une-interface-accessible-en-Jetpack-Compose/main/img/tops/1.jpg"),
            Product(id = 5,name = "Pull vert femme",price = "27.59€",originalPrice = "39.99€",likes = 2,rating = 3.1f,imageDescription = "", category = "Accessories",imageUrl = "https://raw.githubusercontent.com/OpenClassrooms-Student-Center/D-velopper-une-interface-accessible-en-Jetpack-Compose/main/img/tops/2.jpg"),
        )
    //---------------------------------


        //TODO COMPOSITION ----------
        setContent {
            JoiefullTheme {
                Surface{
                   MainComposition(sampleProducts)
                }

            }
        }

    }


    /**
     * Method to setup the splash screen end transition animation.
     */
    private fun initSplashScreenEndAnimation(){
        //Callback that is called when the splash screen is animating to the main content.
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            // Create your custom animation to execute a the end of the splashscreen.
            val slideLeft = ObjectAnimator.ofFloat(splashScreenView,View.TRANSLATION_X,0f,-splashScreenView.width.toFloat())
            slideLeft.interpolator = AccelerateInterpolator()
            slideLeft.duration = 180L
            slideLeft.doOnEnd { splashScreenView.remove() }
            slideLeft.start()
        }
    }

}
