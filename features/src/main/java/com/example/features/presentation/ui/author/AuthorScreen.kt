package com.example.features.presentation.ui.author

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AuthorScreen(navController: NavHostController) {
    Column {
        Text(text = "Pantalla de Autores")
        Button(onClick = {  }) {
            Text("Ir a Detalle de Autor")
        }
    }
}