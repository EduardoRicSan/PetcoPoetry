package com.example.features.presentation.ui.author

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.data.network.NetworkResource
import com.example.core.domain.model.Author
import com.example.features.presentation.ui.common.LoaderComponent
import com.example.features.presentation.viewmodels.author.AuthorViewModel

@Composable
fun AuthorScreen(onItemClicked: (String) -> Unit,
                 onFavoriteClick: () -> Unit) {
    val authorsViewModel: AuthorViewModel = hiltViewModel()
    val authorState = authorsViewModel.authorsUIState.collectAsState().value
    when (authorState) {
        is NetworkResource.Loading -> { LoaderComponent() }

        is NetworkResource.Success -> {
            LazyColumn {
                items(authorState.data.authors) { authorName ->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .clickable {
                            onItemClicked(authorName)
                            Log.d("DETAIL", "${authorName}")
                        }
                    ) {
                        IconButton(
                            onClick = onFavoriteClick,

                            ) {
                            Icon(Icons.Outlined.FavoriteBorder, contentDescription = null)
                        }
                        Text(
                            text = authorName,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }

            }
        }

        is NetworkResource.Error -> {
            Log.d("AUTHORS", "${authorState.message}")
        }

    }
}
