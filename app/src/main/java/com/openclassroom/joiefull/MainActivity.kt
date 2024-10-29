package com.openclassroom.joiefull

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.openclassroom.joiefull.compositions.AdaptiveContent
import com.openclassroom.joiefull.ui.theme.JoiefullTheme
import dagger.hilt.android.AndroidEntryPoint
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.Uri
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.openclassroom.joiefull.compositions.screens.NoInternetScreen

/**
 * Main activity of the application.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //Adaptive navigator
    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    private lateinit var navigator: ThreePaneScaffoldNavigator<Int>

    /**
     * Lifecycle method that is called when the activity is created.
     * @param savedInstanceState The saved state of the activity.
     */
    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        //Install splash screen (keep before the call of super.onCreate())
        installSplashScreen()
        //Init splash screen end animation
        initSplashScreenEndAnimation()
        //Call super.onCreate()
        super.onCreate(savedInstanceState)
        //Edge to edge navigation mode feature
        enableEdgeToEdge()
        //Get the deeplink from the intent (Sharing feature)
        val appLinkData: Uri? = intent.data
        //Main content of the activity
        setContent {
            //Set the adaptive navigator and configure it back navigation behaviour
            navigator = rememberListDetailPaneScaffoldNavigator<Int>()
            //Manage back navigation
            BackHandler(enabled = navigator.canNavigateBack()) {
                navigator.navigateBack()
            }
            //Call the adaptive composition
            JoiefullTheme {
                CheckInternetConnection(appLinkData)
            }
        }

    }

    /**
     *  Lifecycle method that is called when a new intent is received
     *   AND the app is already open.
     *
     *  @param intent The new intent to handle.
     *
     */
    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent.data?.lastPathSegment?.toIntOrNull()?.let {
            navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, it)
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

    /**
     * Method to check the internet connection and display the appropriate screen.
     * @param appLinkData The deeplink from the intent (Sharing feature).
     */
    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    @Composable
    private fun CheckInternetConnection(appLinkData: Uri?){
        val connectivityManager = LocalContext.current.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val isConnected = remember { mutableStateOf(false) }
        // Check the internet connection state
        DisposableEffect(connectivityManager) {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    isConnected.value = true
                }
                override fun onLost(network: Network) {
                    isConnected.value = false
                }
            }
            connectivityManager.registerDefaultNetworkCallback(callback)
            onDispose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }

        if (isConnected.value) {
            // Internet connection is available -- go to main screen app
            AdaptiveContent(navigator, appLinkData)
        } else {
            // Internet connection is not available -- go to no internet screen
            NoInternetScreen()
        }
    }
}
