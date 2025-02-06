package com.example.features.presentation.viewmodels.author

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.network.NetworkResource
import com.example.core.domain.model.Author
import com.example.core.domain.usecase.GetAuthorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorViewModel @Inject constructor(
    private val getAuthorUseCase: GetAuthorUseCase
): ViewModel() {
    private val _authorsUIState = MutableStateFlow<NetworkResource<Author>>(NetworkResource.Loading())
    val authorsUIState: StateFlow<NetworkResource<Author>> = _authorsUIState

    fun getAuthors() {
        viewModelScope.launch {
            getAuthorUseCase.invoke().collect { result ->
                _authorsUIState.value = result
            }
        }
    }
}