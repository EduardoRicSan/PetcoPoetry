package com.example.core.data.network.dto


import com.example.core.domain.model.PoemInfo
import kotlinx.serialization.Serializable

@Serializable
data class PoemInfoResponse(
    val title: String,
    val author: String,
    val lines: List<String>,
    val lineCount: String,
) {

    fun toPoemInfo(): PoemInfo = PoemInfo(
        title, author, lines, lineCount
    )
}