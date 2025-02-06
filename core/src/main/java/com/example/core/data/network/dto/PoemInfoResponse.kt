package com.example.core.data.network.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.domain.model.PoemInfo
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "poems")
data class PoemInfoResponse(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val author: String,
    val lines: List<String>,
    val lineCount: String,
) {

    fun toPoemInfo(): PoemInfo = PoemInfo(
        title, author, lines, lineCount
    )
}