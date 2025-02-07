package com.example.core.data.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.core.data.database.room.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM user_table LIMIT 1")
    suspend fun getUser(): User?
}