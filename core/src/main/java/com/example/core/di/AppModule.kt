package com.example.core.di

import android.content.Context
import android.content.SharedPreferences
import com.example.core.data.network.PoetryApiService
import com.example.core.data.network.PoetryApiServiceImpl
import com.example.core.data.repository.PoetryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
        defaultRequest {
            url("https://poetrydb.org/")
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }

    @Provides
    @Singleton
    fun providePoetryApiService(client: HttpClient): PoetryApiService =
        PoetryApiServiceImpl(client)

    @Provides
    @Singleton
    fun providePoetryRepository(
        apiService: PoetryApiService,
    ): PoetryRepository = PoetryRepository(apiService)

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }
}