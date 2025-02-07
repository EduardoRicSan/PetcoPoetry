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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
    TopAppBar(
        title = { Text("¡Bienvenido!") }
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

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val viewModel: MainViewModel = hiltViewModel()
    val authorState = viewModel.authorsUIState.collectAsState().value
    when (authorState) {
        is NetworkResource.Loading -> { Log.d("AUTHORS", "Loading") }
        is NetworkResource.Success -> { Log.d("AUTHORS", "${authorState.data}") }

        is NetworkResource.Error -> { Log.d("AUTHORS", "${authorState.message}") }

    }
    Box (
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            onClick = {
                viewModel.getAuthors()
               // viewModel.action()
            },
            modifier = modifier
        ) {
            Text(
                text = "Try me $name",
            )
        }
    }
} */

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestWithPoetryTheme {
       // Greeting("Android")
    }
}