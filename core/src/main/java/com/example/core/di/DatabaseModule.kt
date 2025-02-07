package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.data.database.room.dao.FavoriteAuthorDao
import com.example.core.data.database.room.dao.UserDao
import com.example.core.data.database.room.db.AppDatabase
import com.example.core.data.database.room.repository.FavoriteAuthorRepository
import com.example.core.data.database.room.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "poetry_database",
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteAuthorDao(db: AppDatabase): FavoriteAuthorDao {
        return db.favoriteAuthorDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteAuthorRepository(favoriteAuthorDao: FavoriteAuthorDao): FavoriteAuthorRepository {
        return FavoriteAuthorRepository(favoriteAuthorDao)
    }

}