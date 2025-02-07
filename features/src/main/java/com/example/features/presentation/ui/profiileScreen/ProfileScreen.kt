package com.example.features.presentation.ui.profiileScreen

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.features.presentation.ui.common.toDateString
import com.example.features.presentation.viewmodels.profile.ProfileViewModel

private const val IMAGE_TEMP = "https://play-lh.googleusercontent.com/mSV8lRRtM_2q-2rTtOyQdlZcwfuEFYXTYoPQG23gTuHeOp6_EL-1SV8KvjA76llgEg"
@Composable
fun ProfileScreen(navController: NavHostController) {
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val userState = profileViewModel.userState.collectAsState()
    LaunchedEffect(Unit) {
        profileViewModel.getUser()
    }
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = IMAGE_TEMP,
            contentDescription = null,
            modifier = Modifier
                .size(240.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Black, CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Username: ${userState.value?.name ?: "-"}",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
        )

        Text(
            text = "Email: ${userState.value?.email ?: "-"}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary,
        )

        Text(
            text = "Birthday: ${userState.value?.birthday?.toDateString() ?: "-"}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.inversePrimary,
        )

    }
}