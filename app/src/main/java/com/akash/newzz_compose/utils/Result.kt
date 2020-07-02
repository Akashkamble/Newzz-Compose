package com.akash.newzz_compose.utils

/**
 * Created by Akash on 06/06/20
 */

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val errorMessage: String, val showRetry: Boolean = true) : Result<Nothing>()
}
