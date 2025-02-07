package com.example.features.presentation.ui.welcomeScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.twotone.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.features.presentation.ui.common.isValidEmail

@Composable
fun WelcomeScreen(
    onNavigateToAuthorList: () -> Unit,
) {
    var isNavigationActive by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        UserFormComponent {
            isNavigationActive = it
        }
        Button(
            onClick = { onNavigateToAuthorList() },
            enabled = isNavigationActive,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isNavigationActive) MaterialTheme.colorScheme.primaryContainer
                else MaterialTheme.colorScheme.onTertiary,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Continue to Poetry")
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

    TextField(
        value = name,
        onValueChange = { name = it },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Name Icon",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        label = { Text("Name") },
        modifier = Modifier.fillMaxWidth()
    )

    TextField(
        value = email,
        onValueChange = { email = it },
        label = { Text("Email") },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "Email Icon",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        modifier = Modifier.fillMaxWidth()
    )

    TextField(
        value = birthday,
        onValueChange = { birthday = it },
        label = { Text("Birthday") },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.TwoTone.DateRange,
                contentDescription = "BirthDay Icon",
                tint = MaterialTheme.colorScheme.primary
            )
        },
        modifier = Modifier.fillMaxWidth()
    )

    if (name.isNotEmpty() && email.isNotEmpty()
        && birthday.isNotEmpty() && email.isValidEmail()
    ) isValidData(true) else isValidData(false)
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    MaterialTheme {
        WelcomeScreen { }
    }
}