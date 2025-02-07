package com.example.features.presentation.viewmodels.poem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.network.NetworkResource
import com.example.core.domain.model.Author
import com.example.core.domain.model.PoemInfo
import com.example.core.domain.model.PoemTitle
import com.example.core.domain.usecase.PoemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PoemViewModel @Inject constructor(
    private val poemUseCase: PoemUseCase
): ViewModel() {
    private val _poemTitleUIState = MutableStateFlow<NetworkResource<List<PoemTitle>>>(NetworkResource.Loading())
    val poemTitleUIState: StateFlow<NetworkResource<List<PoemTitle>>> = _poemTitleUIState

    private val _poemInfoUIState = MutableStateFlow<NetworkResource<List<PoemInfo>>>(NetworkResource.Loading())
    val poemInfoUIState: StateFlow<NetworkResource<List<PoemInfo>>> = _poemInfoUIState


    fun getPoemsByAuthor(authorName: String) {
        viewModelScope.launch {
            poemUseCase.getPoemsByAuthor(authorName).collect { result ->
                _poemTitleUIState.value = result
            }
        }
    }

    fun getPoemInfo(authorName: String, poemTitle: String) {
        viewModelScope.launch {
            poemUseCase.getPoemInfo(
            authorName = authorName, poemTitle = poemTitle
            ).collect { result ->
                _poemInfoUIState.value = result
            }
        }
    }
}