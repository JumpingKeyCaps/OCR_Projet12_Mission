package com.openclassroom.joiefull.di
/**
 * Object App Config to easy configure the application.
 */
object AppConfig {

    /** The Joiefull server API base URL
     * - End-point to get the products list from the joiefull server API
     */
    const val BASE_URL = "https://raw.githubusercontent.com/OpenClassrooms-Student-Center/D-velopper-une-interface-accessible-en-Jetpack-Compose/main/api/"

    /**
     * Adaptive pane configuration.
     * - (The % of the screen width used to display the main pane screen)
     * - 1.0f = 100% of the screen width
     * - 0.0f = 0% of the screen width
     */
    const val PAN_DIVIDER_RATIO = 0.6f

}