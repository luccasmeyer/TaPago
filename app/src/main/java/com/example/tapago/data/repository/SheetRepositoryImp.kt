package com.example.tapago.data.repository

import com.example.tapago.data.daos.SheetDao
import com.example.tapago.data.entities.ExercisesEntity
import com.example.tapago.data.entities.SheetsEntity
import com.example.tapago.data.utils.safeDbCall
import com.example.tapago.domain.repository.ITaPagoRepository
import com.example.tapago.domain.wrapper.IResourceRoom

class SheetRepositoryImp(
    private val dao: SheetDao
): ITaPagoRepository<SheetsEntity>{

    override suspend fun select(): IResourceRoom<List<SheetsEntity>>  = safeDbCall {
        dao.findAll()
    }

    override suspend fun insert(
        item: SheetsEntity
    ): IResourceRoom<Unit> = safeDbCall {
        dao.insertSheet(item)
    }

    override suspend fun update(
        item: SheetsEntity
    ): IResourceRoom<Unit> = safeDbCall {
        TODO("Not yet implemented")
    }

    override suspend fun delete(
        item: SheetsEntity
    ): IResourceRoom<Unit> = safeDbCall {
        dao.deleteSheet(item)
    }
}