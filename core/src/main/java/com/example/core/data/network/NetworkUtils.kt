package com.example.core.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

suspend fun <T> safeApiCall(call: suspend () -> T): Flow<NetworkResource<T>> = flow {
    emit(NetworkResource.Loading())
    try {
        val response = withContext(Dispatchers.IO) { call() }
        emit(NetworkResource.Success(response))
    } catch (e: Exception) {
        emit(NetworkResource.Error(e.message ?: "Unknown Error"))
    }
}.flowOn(Dispatchers.IO)