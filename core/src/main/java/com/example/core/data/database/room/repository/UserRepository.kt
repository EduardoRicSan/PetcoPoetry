package com.example.core.data.database.room.repository

import com.example.core.data.database.room.dao.UserDao
import com.example.core.data.database.room.entity.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun insertUser(user: User) = userDao.insert(user)

    suspend fun getUser() : User? = userDao.getUser()
}
