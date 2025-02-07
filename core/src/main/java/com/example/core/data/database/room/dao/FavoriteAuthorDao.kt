package com.example.core.data.database.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.database.room.entity.FavoriteAuthor
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteAuthorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(author: FavoriteAuthor)

    @Delete
    suspend fun removeFavorite(author: FavoriteAuthor)

    @Query("SELECT * FROM favorite_authors")
    fun getAllFavorites(): Flow<List<FavoriteAuthor>>
}