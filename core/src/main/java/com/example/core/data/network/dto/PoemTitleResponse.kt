package com.example.core.data.network.dto

import com.example.core.domain.model.PoemTitle
import kotlinx.serialization.Serializable

@Serializable
data class PoemTitleResponse(
    val title: String,
) {

    fun toPoemTitle(): PoemTitle = PoemTitle(title)
}