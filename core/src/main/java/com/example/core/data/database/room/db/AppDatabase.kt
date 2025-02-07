package com.example.core.data.database.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.database.room.dao.UserDao
import com.example.core.data.database.room.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}