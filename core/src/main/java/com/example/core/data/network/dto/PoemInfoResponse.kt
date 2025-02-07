package com.example.core.data.network.dto


import com.example.core.domain.model.PoemInfo
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class PoemInfoResponse(
    val title: String,
    val author: String,
    val lines: List<String>,
   @JsonNames("linecount", "lineCount" ) val lineCount: String,
) {

    fun toPoemInfo(): PoemInfo = PoemInfo(
        title, author, lines, lineCount
    )
}