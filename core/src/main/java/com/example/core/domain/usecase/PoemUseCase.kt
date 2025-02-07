package com.example.core.domain.usecase

import com.example.core.data.network.NetworkResource
import com.example.core.data.repository.PoetryRepository
import com.example.core.domain.model.PoemInfo
import com.example.core.domain.model.PoemTitle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PoemUseCase @Inject constructor(
    private val poetryRepository: PoetryRepository
) {
    suspend fun getPoemsByAuthor(authorName: String) : Flow<NetworkResource<List<PoemTitle>>> {
        return poetryRepository.getPoemsByAuthor(authorName)
    }

    suspend fun getPoemInfo(authorName: String, poemTitle: String) : Flow<NetworkResource<List<PoemInfo>>> {
        return poetryRepository.getPoemInfo(authorName, poemTitle)
    }
}