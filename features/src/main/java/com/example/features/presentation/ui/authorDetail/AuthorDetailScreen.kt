package com.example.features.presentation.ui.authorDetail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.data.network.NetworkResource
import com.example.core.domain.model.PoemTitle
import com.example.features.presentation.ui.common.LoaderComponent
import com.example.features.presentation.viewmodels.poem.PoemViewModel
import kotlinx.coroutines.launch

@Composable
fun AuthorDetailScreen(authorName: String) {
    val viewModel: PoemViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.getPoemsByAuthor(authorName)
    }
    val authorState = viewModel.poemTitleUIState.collectAsState().value
    when (authorState) {
        is NetworkResource.Loading -> {
            LoaderComponent()
        }

        is NetworkResource.Success -> {
            Log.d("DETAIL", "${authorState.data}")
            PoemsComponent(authorState.data, authorName)
        }

        is NetworkResource.Error -> {
            Log.d("DETAIL", "${authorState.message}")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PoemsComponent(
    data: List<PoemTitle>,
    authorName: String,
) {
    val viewModel: PoemViewModel = hiltViewModel()
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var showLoader by remember { mutableStateOf(false) }
    val poemInfoUIState = viewModel.poemInfoUIState.collectAsState().value

    LaunchedEffect(poemInfoUIState) {
        if (poemInfoUIState is NetworkResource.Success) {
            showLoader = false
            showBottomSheet = true
        }
    }
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                (poemInfoUIState as? NetworkResource.Success)?.data?.first()?.let { poemInfo ->
                    Text(
                        text = poemInfo.title,
                        style = MaterialTheme.typography.displaySmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = poemInfo.author,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(16.dp)
                    )
                    poemInfo.lines.forEach {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }


            }
        }
    }

    LazyColumn {
        items(data) { poemTitle ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .clickable {
                    showLoader = true
                    viewModel.getPoemInfo(
                        authorName = authorName,
                        poemTitle = poemTitle.title
                    )
                }
            ) {
                Text(
                    text = poemTitle.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(16.dp)
                )
            }
        }
    }
    if (showLoader) LoaderComponent()
}