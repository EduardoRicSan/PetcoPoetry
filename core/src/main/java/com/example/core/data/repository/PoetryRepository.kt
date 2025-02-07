package com.example.core.data.repository

import com.example.core.data.network.NetworkResource
import com.example.core.data.network.PoetryApiService
import com.example.core.data.network.dto.PoemInfoResponse
import com.example.core.data.network.dto.PoemTitleResponse
import com.example.core.data.network.safeApiCall
import com.example.core.domain.model.Author
import com.example.core.domain.model.PoemInfo
import com.example.core.domain.model.PoemTitle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PoetryRepository @Inject constructor(
    private val poetryApiService: PoetryApiService,
) {
    suspend fun getAuthors(): Flow<NetworkResource<Author>> = safeApiCall {
        poetryApiService.getAuthors().toAuthor()
    }

    suspend fun getPoemsByAuthor(authorName: String): Flow<NetworkResource<List<PoemTitle>>> = safeApiCall {
        poetryApiService.getPoemsByAuthor(authorName).map { it.toPoemTitle() }
    }

    suspend fun getPoemInfo(
        authorName: String,
        poemTitle: String,
    ): Flow<NetworkResource<List<PoemInfo>>> = safeApiCall {
        poetryApiService.getPoemInfo(authorName, poemTitle).map { it.toPoemInfo() }
    }
}