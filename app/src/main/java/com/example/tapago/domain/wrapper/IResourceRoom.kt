package com.example.tapago.domain.wrapper

sealed interface IResourceRoom<out T> {
    data class Success<T>(val data: T): IResourceRoom<T>
    data class Error(val message: String, val exception: Exception? = null): IResourceRoom<Nothing>
}