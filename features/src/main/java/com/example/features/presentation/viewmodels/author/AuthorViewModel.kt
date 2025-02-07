package com.example.features.presentation.viewmodels.author

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.database.room.entity.FavoriteAuthor
import com.example.core.data.database.room.repository.FavoriteAuthorRepository
import com.example.core.data.network.NetworkResource
import com.example.core.domain.model.Author
import com.example.core.domain.usecase.GetAuthorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorViewModel @Inject constructor(
    private val getAuthorUseCase: GetAuthorUseCase,
    private val favoriteAuthorRepository: FavoriteAuthorRepository
): ViewModel() {
    init {
        getAuthors()
        getFavoriteAuthors()
    }

    private val _authorsUIState = MutableStateFlow<NetworkResource<Author>>(NetworkResource.Loading())
    val authorsUIState: StateFlow<NetworkResource<Author>> = _authorsUIState

    private val _favoriteAuthors = MutableStateFlow<List<FavoriteAuthor>>(emptyList())
    val favoriteAuthors: StateFlow<List<FavoriteAuthor>> = _favoriteAuthors

    private fun getAuthors() {
        viewModelScope.launch(Dispatchers.IO) {
            getAuthorUseCase.invoke().collect { result ->
                _authorsUIState.value = result
            }
        }
    }

    private fun getFavoriteAuthors() {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteAuthorRepository.getFavoriteAuthors().collect { authors ->
                _favoriteAuthors.value = authors
            }
        }
    }


    fun toggleFavorite(authorName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val isFavorite = _favoriteAuthors.value.any { it.name == authorName }
            if (isFavorite) {
                favoriteAuthorRepository.removeFavoriteAuthor(authorName)
            } else {
                favoriteAuthorRepository.addFavoriteAuthor(authorName)
            }
        }
    }
}