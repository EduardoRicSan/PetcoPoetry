package com.example.testwithpoetry.navHost.PoetryNavHost

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.features.presentation.ui.author.AuthorScreen
import com.example.features.presentation.ui.profiileScreen.ProfileScreen

@Composable
fun PoetryNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Author.route,
        modifier = modifier
    ) {
        composable(Screen.Author.route) { AuthorScreen(navController) }
        composable(Screen.Profile.route) { ProfileScreen(navController) }
    }
}

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Author : Screen("author", "Poesía", Icons.Outlined.Menu)
    object Profile : Screen("profile", "Cuenta", Icons.Outlined.AccountCircle)
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}