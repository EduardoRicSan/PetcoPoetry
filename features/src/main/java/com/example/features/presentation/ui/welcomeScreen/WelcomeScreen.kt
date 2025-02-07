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
import androidx.compose.material3.OutlinedTextField
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.data.database.room.entity.User
import com.example.features.presentation.ui.common.DatePickerTextField
import com.example.features.presentation.ui.common.isValidEmail
import com.example.features.presentation.ui.common.toDateLong
import com.example.features.presentation.viewmodels.profile.ProfileViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            UserFormComponent {
                isNavigationActive = it
            }
        }
        Button(
            onClick = { onNavigateToAuthorList() },
            enabled = isNavigationActive,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isNavigationActive) MaterialTheme.colorScheme.primaryContainer
                else MaterialTheme.colorScheme.onTertiary,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.align(Alignment.BottomCenter)
            ) {
            Text(text = "Continue to Poetry")
        }
    }
}

@Composable
fun UserFormComponent(
    isValidData: (Boolean) -> Unit,
) {
    val profileViewModel: ProfileViewModel = hiltViewModel()
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var selectedDate by rememberSaveable { mutableStateOf("") }
    val formattedDate = remember(selectedDate) {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        if (selectedDate.isNotEmpty()) {
            sdf.format(Date(selectedDate.toLong()))
        } else {
            ""
        }
    }

    OutlinedTextField(
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

    OutlinedTextField(
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

    DatePickerTextField(
        label = "Select Date",
        selectedDate = formattedDate,
        onDateSelected = { millis ->
            selectedDate = millis.toString()
        }
    )



    if (name.isNotEmpty() && email.isNotEmpty()
        && selectedDate.isNotEmpty() && email.isValidEmail()
    ) {
        isValidData(true)
        profileViewModel.saveUser(User(name = name, email = email, birthday = formattedDate.toDateLong()))
    } else {
        isValidData(false)
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    MaterialTheme {
        WelcomeScreen { }
    }
}