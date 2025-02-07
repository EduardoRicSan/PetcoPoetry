package com.example.testwithpoetry.navHost

import android.util.Log
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.features.presentation.ui.author.AuthorScreen
import com.example.features.presentation.ui.authorDetail.AuthorDetailScreen
import com.example.features.presentation.ui.profiileScreen.ProfileScreen
import com.example.features.presentation.ui.welcomeScreen.WelcomeScreen
import com.example.testwithpoetry.MainViewModel

private const val GRAPH = "poetry_route"
private const val START_DESTINATION = "welcome_route"
private const val AUTHOR_ROUTE = "author_route"
private const val PROFILE_ROUTE = "profile_route"
private const val AUTHOR_DETAIL_ROUTE = "author_detail_route"

@Composable
fun PoetryNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val mainViewModel: MainViewModel = hiltViewModel()

    val isFirstLaunch by mainViewModel.isFirstLaunch.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screen.Welcome.route,
        //startDestination = if (isFirstLaunch) Screen.Welcome.route else Screen.Author.route,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        modifier = modifier
    ) {

        composable(Screen.Welcome.route) {
            WelcomeScreen {
                navController.navigate(Screen.Author.route)
            }
        }
        composable(Screen.Author.route) {
            AuthorScreen(
                onItemClicked = { authorName ->
                    navController.navigate("authorDetail/$authorName")
                },
            )
        }
        composable("authorDetail/{authorName}") { backStackEntry ->
            val authorName = backStackEntry.arguments?.getString("authorName") ?: ""
            Log.d("DETAIL", "$authorName")
            AuthorDetailScreen(authorName)
        }
        composable(Screen.Profile.route) { ProfileScreen(navController) }
    }

}

/*NavHost(
       navController = navController,
       startDestination = START_DESTINATION,
       enterTransition = { fadeIn() },
       exitTransition = { fadeOut() },
       modifier = modifier
   ) {
       poetryGraph(navController = navController)
   } */

fun NavGraphBuilder.poetryGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = START_DESTINATION,
        route = GRAPH
    ) {
        composable(route = START_DESTINATION) {
            WelcomeScreen() {
                navController.navigate(AUTHOR_ROUTE)
            }
        }

        composable(route = AUTHOR_ROUTE) {
            AuthorScreen(
                onItemClicked = {
                    navController.navigate(AUTHOR_DETAIL_ROUTE)
                },
            )
        }

        /*composable("${AUTHOR_DETAIL_ROUTE}/{authorName}") { backStackEntry ->
            val authorName = backStackEntry.arguments?.getString("authorName") ?: ""
            AuthorDetailScreen  {
                navController.navigate("$AUTHOR_DETAIL_ROUTE/$authorName")
            }
        } */

        composable(route = PROFILE_ROUTE) {
            ProfileScreen(navController)
        }
    }
}

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Author : Screen("author", "Poetry", Icons.Outlined.Menu)
    object Profile : Screen("profile", "Account", Icons.Outlined.AccountCircle)
    object Welcome : Screen("welcome", "Welcome", Icons.Outlined.Home)
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}