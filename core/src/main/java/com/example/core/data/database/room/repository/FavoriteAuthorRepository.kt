package com.example.core.data.database.room.repository

import com.example.core.data.database.room.dao.FavoriteAuthorDao
import com.example.core.data.database.room.entity.FavoriteAuthor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteAuthorRepository @Inject constructor(
    private val favoriteAuthorDao: FavoriteAuthorDao
) {

    fun getFavoriteAuthors(): Flow<List<FavoriteAuthor>> = favoriteAuthorDao.getAllFavorites()

    suspend fun addFavoriteAuthor(authorName: String) {
        favoriteAuthorDao.addFavorite(FavoriteAuthor(authorName))
    }

    suspend fun removeFavoriteAuthor(authorName: String) {
        favoriteAuthorDao.removeFavorite(FavoriteAuthor(authorName))
    }
}