package com.example.features.presentation.ui.author

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.data.network.NetworkResource
import com.example.core.domain.model.Author
import com.example.features.presentation.ui.common.LoaderComponent
import com.example.features.presentation.viewmodels.author.AuthorViewModel

@Composable
fun AuthorScreen(onItemClicked: (String) -> Unit,) {
    val context = LocalContext.current
    val authorsViewModel: AuthorViewModel = hiltViewModel()
    val favoriteAuthors by authorsViewModel.favoriteAuthors.collectAsState()
    when (val authorState = authorsViewModel.authorsUIState.collectAsState().value) {
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
                        }
                    ) {
                        IconButton(onClick = {
                            authorsViewModel.toggleFavorite(authorName)
                            Toast.makeText(context, "Author added to Favorites", Toast.LENGTH_LONG).show()
                        }) {
                            val icon = if (favoriteAuthors.any {it.name == authorName}) {
                                Icons.Outlined.Favorite
                            } else {
                                Icons.Outlined.FavoriteBorder
                            }
                            Icon(icon, contentDescription = null)
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
