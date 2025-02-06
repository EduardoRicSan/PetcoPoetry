package com.example.core.domain.usecase

import com.example.core.data.network.NetworkResource
import com.example.core.data.network.dto.AuthorResponse
import com.example.core.data.repository.PoetryRepository
import com.example.core.domain.model.Author
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAuthorUseCase @Inject constructor(
    private val poetryRepository: PoetryRepository
) {
    suspend fun invoke(): Flow<NetworkResource<Author>> {
        return poetryRepository.getAuthors()
    }
}