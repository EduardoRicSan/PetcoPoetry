package com.example.testwithpoetry

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.data.network.NetworkResource
import com.example.testwithpoetry.ui.theme.TestWithPoetryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestWithPoetryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
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
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestWithPoetryTheme {
        Greeting("Android")
    }
}