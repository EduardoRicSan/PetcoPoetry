package com.example.core.data.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): PoetryDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            PoetryDatabase::class.java,
            "poetry_database"
        ).build()
    }

    @Provides
    fun providePoetryDao(database: PoetryDatabase): PoetryDao {
        return database.poetryDao()
    }
}