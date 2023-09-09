package com.bangkit.githubuser.data

sealed class Result<out R> private constructor(){
    data class Success<out T>(val data: T): Result<T>()
    data class Error(val error: String): com.bangkit.githubuser.data.Result<Nothing>()

    object Loading: com.bangkit.githubuser.data.Result<Nothing>()
}
