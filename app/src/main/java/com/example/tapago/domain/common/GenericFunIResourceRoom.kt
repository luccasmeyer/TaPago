package com.example.tapago.domain.common

import com.example.tapago.domain.wrapper.IResourceRoom

fun <T, R> IResourceRoom<T>.map(transform: (T) -> R): IResourceRoom<R> {
    return when (this) {
        is IResourceRoom.Success -> IResourceRoom.Success(transform(this.data))
        is IResourceRoom.Error -> this
    }
}