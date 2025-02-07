package com.example.testwithpoetry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.database.room.repository.UserRepository
import com.example.core.data.database.sharedPreferences.PreferencesRepository
import com.example.core.data.network.NetworkResource
import com.example.core.domain.model.Author
import com.example.core.domain.usecase.GetAuthorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
): ViewModel() {

    private val _isFirstLaunch = MutableStateFlow(true)
    val isFirstLaunch: StateFlow<Boolean> = _isFirstLaunch

    init {
        checkFirstLaunch()
    }

    private fun checkFirstLaunch() {
        val firstLaunch = preferencesRepository.isFirstLaunch()
        _isFirstLaunch.value = firstLaunch

        if (firstLaunch) {
            preferencesRepository.setFirstLaunchDone()
        }
    }

}