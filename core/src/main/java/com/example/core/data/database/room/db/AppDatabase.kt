package com.example.core.data.database.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.database.room.dao.FavoriteAuthorDao
import com.example.core.data.database.room.dao.UserDao
import com.example.core.data.database.room.entity.FavoriteAuthor
import com.example.core.data.database.room.entity.User

@Database(entities = [User::class, FavoriteAuthor::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun favoriteAuthorDao() : FavoriteAuthorDao
}