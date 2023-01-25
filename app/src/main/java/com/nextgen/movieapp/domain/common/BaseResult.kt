package com.nextgen.movieapp.domain.common

sealed class BaseResult<out R> private constructor(){
    data class Success<out T>(val data: T): BaseResult<T>()
    data class Error(val message: String): BaseResult<Nothing>()
}
