package com.openclassroom.joiefull

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.openclassroom.joiefull.compositions.JoiefullApp
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity of the application.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Lifecycle method that is called when the activity is created.
     * @param savedInstanceState The saved state of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //Install splash screen (keep before the call of super.onCreate())
        installSplashScreen()
        //Init splash screen end animation
        initSplashScreenEndAnimation()
        //Call super.onCreate()
        super.onCreate(savedInstanceState)
        //Edge to edge navigation mode feature
        enableEdgeToEdge()
        //Main content of the activity
        setContent {
            JoiefullApp()
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
