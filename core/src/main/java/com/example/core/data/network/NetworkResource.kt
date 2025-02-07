package com.example.core.data.network

sealed class NetworkResource<T> {
    class Success<T>(val data: T) : NetworkResource<T>()
    class Error<T>(val message: String) : NetworkResource<T>()
    class Loading<T> : NetworkResource<T>()
}