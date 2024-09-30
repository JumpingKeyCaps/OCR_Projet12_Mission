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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.openclassroom.joiefull.ui.theme.JoiefullTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //Install splash screen (keep before the call of super.onCreate())
        installSplashScreen()
        super.onCreate(savedInstanceState)

        //TODO SPLASH SCREEN ----------
        initSplashScreenEndAnimation()








//TODO COMPOSITION ----------

        enableEdgeToEdge()
        setContent {
            JoiefullTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
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





@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JoiefullTheme {
        Greeting("Android")
    }
}