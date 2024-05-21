package com.randrez.countriesapp.base

sealed class DataState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Error<T>(message: String?, data: T? = null) :
        DataState<T>(data = data, message = message)
    class Success<T>(data: T) : DataState<T>(data)
}