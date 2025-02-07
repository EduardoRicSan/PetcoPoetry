package com.example.features.presentation.ui.welcomeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeScreen(
    onNavigateToAuthorList: () -> Unit,
) {
    var isNavigationActive by rememberSaveable { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            UserFormComponent {

            }
        }
        FloatingActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = onNavigateToAuthorList,

        ) {
            Icon(Icons.Outlined.List, contentDescription = null)
        }
    }
}

@Composable
fun UserFormComponent(
    isValidData: (Boolean) -> Unit,
) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var birthday by rememberSaveable { mutableStateOf("") }
    var isValidData by rememberSaveable { mutableStateOf(false) }

    TextField(
        value = name,
        onValueChange = { name = it },
        label = { Text("Name") },
        modifier = Modifier.fillMaxWidth()
    )

    TextField(
        value = email,
        onValueChange = { name = it },
        label = { Text("Email") },
        modifier = Modifier.fillMaxWidth()
    )

    TextField(
        value = birthday,
        onValueChange = { name = it },
        label = { Text("Birthday") },
        modifier = Modifier.fillMaxWidth()
    )

    if (name.isNotEmpty() && email.isNotEmpty() && birthday.isNotEmpty())
        isValidData = true
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    MaterialTheme {
        WelcomeScreen {  }
    }
}