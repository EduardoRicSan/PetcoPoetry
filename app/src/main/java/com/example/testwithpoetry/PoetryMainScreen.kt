package com.example.testwithpoetry

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.testwithpoetry.navHost.PoetryNavHost

@Composable
fun PoetryMainScreen(navHostController: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { PoetryTopBar() },
        bottomBar = { PoetryBottomBar(navHostController) }
    ) { innerPaddings ->
        PoetryNavHost(navHostController,
            modifier = Modifier.padding(innerPaddings))
    }
}