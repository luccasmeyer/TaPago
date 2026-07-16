package com.example.tapago.data.repository

import com.example.tapago.data.daos.SheetDao
import com.example.tapago.data.entities.SheetsEntity
import com.example.tapago.data.mapper.toDomain
import com.example.tapago.data.utils.safeDbCall
import com.example.tapago.domain.common.map
import com.example.tapago.domain.model.Sheet
import com.example.tapago.domain.repository.ITaPagoRepository
import com.example.tapago.domain.wrapper.IResourceRoom
import kotlin.collections.map

class SheetRepositoryImp(
    private val dao: SheetDao
): ITaPagoRepository<SheetsEntity>{

    suspend fun selectSheet(): IResourceRoom<List<Sheet>>{
        val sheet = dao.findAll()

        return safeDbCall { sheet.map { it.toDomain() } }
    }

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