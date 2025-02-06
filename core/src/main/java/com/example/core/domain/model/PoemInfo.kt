package com.example.core.domain.model

data class PoemInfo(
    val title: String,
    val author: String,
    val lines: List<String>,
    val lineCount: String,
)