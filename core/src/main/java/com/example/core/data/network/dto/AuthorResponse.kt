package com.example.core.data.network.dto

import com.example.core.domain.model.Author
import kotlinx.serialization.Serializable

@Serializable
data class AuthorResponse(
    val authors: List<String>
) {

    fun toAuthor(): Author = Author(
        authors = authors
    )
}