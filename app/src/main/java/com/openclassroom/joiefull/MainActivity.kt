package com.openclassroom.joiefull

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.openclassroom.joiefull.composition.Greeting
import com.openclassroom.joiefull.composition.ProductCard
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




        //TODO COMPOSITION ----------
        setContent {
            JoiefullTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    //Product card
                    ProductCard(
                        title = "Veste urbaine",
                        price = "89$",
                        imageUrl = "https://media.glamour.com/photos/607f2749febb5e66fe7c52cf/1:1/w_1200,h_1200,c_limit/terry%20cloth%20trend_jumpsuit.jpg",
                        modifier = Modifier.padding(innerPadding)
                    )



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
