package com.example.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.network.dto.PoemInfoResponse

@Database(entities = [PoemInfoResponse::class], version = 1, exportSchema = false)
abstract class PoetryDatabase : RoomDatabase() {
    abstract fun poetryDao(): PoetryDao
}