package com.example.tapago.data.repository

import com.example.tapago.data.daos.ExerciseDao
import com.example.tapago.data.entities.ExercisesEntity
import com.example.tapago.data.utils.safeDbCall
import com.example.tapago.domain.repository.ITaPagoRepository
import com.example.tapago.domain.wrapper.IResourceRoom

class ExerciseRepositoryImp(
    private val dao: ExerciseDao
): ITaPagoRepository<ExercisesEntity> {

    override suspend fun select(): IResourceRoom<List<ExercisesEntity>> = safeDbCall {
        dao.findAll()
    }

    override suspend fun insert(
        item: ExercisesEntity
    ): IResourceRoom<Unit> = safeDbCall {
        dao.insertExercise(item)
    }

    override suspend fun update(
        item: ExercisesEntity
    ): IResourceRoom<Unit> = safeDbCall {
        TODO("Not yet implemented")
    }

    override suspend fun delete(
        item: ExercisesEntity
    ): IResourceRoom<Unit> = safeDbCall {
        dao.deleteExercise(item)
    }
}