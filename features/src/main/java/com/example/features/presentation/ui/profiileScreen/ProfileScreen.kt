package com.example.features.presentation.ui.profiileScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun ProfileScreen(navController: NavHostController) {
    Column {
        Text(text = "Pantalla de Perfil")
        Button(onClick = {  }) {
            Text("Helloooo")
        }
    }
}