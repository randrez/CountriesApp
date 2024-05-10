package com.randrez.countriesapp.data.resource

sealed class Result<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Error<T>(message: String?, data: T? = null) :
        Result<T>(data = data, message = message)
    class Success<T>(data: T) : Result<T>(data)
}