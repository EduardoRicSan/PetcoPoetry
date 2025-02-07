package com.example.core.data.database.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_authors")
data class FavoriteAuthor(
    @PrimaryKey val name: String
)
