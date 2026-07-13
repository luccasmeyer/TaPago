package com.example.tapago.domain.repository

import com.example.tapago.domain.wrapper.IResourceRoom

interface ITaPagoRepository<T> {

    suspend fun select(): IResourceRoom<List<T>>
    suspend fun insert(item: T): IResourceRoom<Unit>
    suspend fun update(item: T): IResourceRoom<Unit>
    suspend fun delete(item: T): IResourceRoom<Unit>
}