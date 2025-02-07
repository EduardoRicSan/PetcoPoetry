package com.example.core.data.network

import com.example.core.data.network.dto.AuthorResponse
import com.example.core.data.network.dto.PoemInfoResponse
import com.example.core.data.network.dto.PoemTitleResponse
import com.example.core.domain.model.PoemTitle
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface PoetryApiService {
    suspend fun getAuthors(): AuthorResponse
    suspend fun getPoemsByAuthor(
        authorName: String,
    ): List<PoemTitleResponse>
    suspend fun getPoemInfo(
        authorName: String,
        poemTitle: String,
    ): List<PoemInfoResponse>
}

class PoetryApiServiceImpl(private val client: HttpClient) : PoetryApiService {

    override suspend fun getAuthors(): AuthorResponse =
        client.get("author").body()

    override suspend fun getPoemsByAuthor(authorName: String): List<PoemTitleResponse> =
        client.get("author/$authorName/title").body()

    override suspend fun getPoemInfo(authorName: String, poemTitle: String): List<PoemInfoResponse> =
        client.get("author,title/$authorName;$poemTitle").body()


}