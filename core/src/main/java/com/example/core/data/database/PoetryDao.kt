package com.example.core.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.data.network.dto.PoemInfoResponse
import com.example.core.domain.model.PoemInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface PoetryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoem(poem: PoemInfoResponse)

    @Query("SELECT * FROM poems WHERE author = :authorName")
    fun getPoemsByAuthor(authorName: String): Flow<List<PoemInfo>>
}