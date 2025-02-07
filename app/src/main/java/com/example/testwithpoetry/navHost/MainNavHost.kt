package com.example.testwithpoetry.navHost

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.features.presentation.ui.welcomeScreen.WelcomeScreen
import com.example.testwithpoetry.MainViewModel
import com.example.testwithpoetry.PoetryMainScreen


@Composable
fun MainNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val mainViewModel: MainViewModel = hiltViewModel()
    val isFirstLaunch by mainViewModel.isFirstLaunch.collectAsState()

    NavHost(
        navController = navHostController,
        startDestination = if(isFirstLaunch) MainScreen.WelcomeSC.route else MainScreen.MainSC.route,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        modifier = modifier
    ) {
        composable(MainScreen.WelcomeSC.route) {
            WelcomeScreen {
                navHostController.navigate(MainScreen.MainSC.route)
            }
        }
        composable(MainScreen.MainSC.route) {
            PoetryMainScreen(navHostController)
        }

    }

}


sealed class MainScreen(val route: String) {
    object MainSC : MainScreen("main_route")
    object WelcomeSC : MainScreen("welcome_route")
}