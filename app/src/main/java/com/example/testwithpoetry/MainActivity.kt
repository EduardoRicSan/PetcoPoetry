package com.example.testwithpoetry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.features.presentation.viewmodels.profile.ProfileViewModel
import com.example.testwithpoetry.navHost.PoetryNavHost
import com.example.testwithpoetry.navHost.Screen
import com.example.testwithpoetry.navHost.currentRoute
import com.example.testwithpoetry.ui.theme.TestWithPoetryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestWithPoetryTheme {
               PoetryMainScreen()
            }
        }
    }
}

@Composable
fun PoetryMainScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { PoetryTopBar() },
        bottomBar = { PoetryBottomBar(navController) }
    ) { innerPaddings ->
        PoetryNavHost(navController,
            modifier = Modifier.padding(innerPaddings))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PoetryTopBar() {
    val profileViewModel: ProfileViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        profileViewModel.getUser()
    }
    val userState = profileViewModel.userState.collectAsState()

    val welcomeMessage = stringResource(R.string.label_welcome, userState.value?.name.orEmpty())
    TopAppBar(
        title = { Text(welcomeMessage) }
    )
}

@Composable
fun PoetryBottomBar(navController: NavHostController) {
    val items = listOf(Screen.Author, Screen.Profile)
    val currentRoute = currentRoute(navController)
    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.route) },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                onClick = { navController.navigate(screen.route) }
            )
        }
    }
}
