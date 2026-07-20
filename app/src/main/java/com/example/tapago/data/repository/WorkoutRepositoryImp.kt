package com.example.tapago.data.repository

import androidx.room.withTransaction
import com.example.tapago.AppDatabase
import com.example.tapago.data.daos.ExerciseSheetDao
import com.example.tapago.data.daos.SheetDao
import com.example.tapago.data.entities.ExercisesSheetEntity
import com.example.tapago.data.entities.SheetsEntity
import com.example.tapago.data.mapper.toDomain
import com.example.tapago.data.utils.safeDbCall
import com.example.tapago.domain.model.Sheet
import com.example.tapago.domain.model.workout.Workout
import com.example.tapago.domain.repository.ITaPagoRepository
import com.example.tapago.domain.wrapper.IResourceRoom
import kotlin.collections.map

class WorkoutRepositoryImp(
    private var database: AppDatabase,
    private var sheetDao: SheetDao,
    private var exerciseSheetDao: ExerciseSheetDao
): ITaPagoRepository<SheetsEntity>{

    suspend fun selectSheet(): IResourceRoom<List<Sheet>>{
        val sheet = sheetDao.findAll()

        return safeDbCall { sheet.map { it.toDomain() } }
    }

    suspend fun createSheet(workout: Workout): IResourceRoom<Unit> {
        return try {
            val sheetEntity = SheetsEntity(
                nameSheet = workout.nameSheet,
                qtdExercise = workout.listExercise.size
            )

            database.withTransaction {
                val sheetId = sheetDao.insertSheet(sheetEntity)

                val sheetBodyList = workout.listExercise.map { exercise ->
                    ExercisesSheetEntity(
                        exerciseId = exercise.idExercise,
                        sheetId = sheetId.toInt(),
                        qtdSets = exercise.qtdSets
                    )
                }

                exerciseSheetDao.insertExercisesSheet(sheetBodyList)
            }

            IResourceRoom.Success(Unit)

        } catch (e: Exception) {
            IResourceRoom.Error(e.message ?: "Erro ao salvar a planilha")
        }
    }

    override suspend fun select(): IResourceRoom<List<SheetsEntity>>  = safeDbCall {
        sheetDao.findAll()
    }

    override suspend fun insert(
        item: SheetsEntity
    ): IResourceRoom<Unit> = safeDbCall {
        sheetDao.insertSheet(item)
    }

    override suspend fun update(
        item: SheetsEntity
    ): IResourceRoom<Unit> = safeDbCall {
        TODO("Not yet implemented")
    }

    override suspend fun delete(
        item: SheetsEntity
    ): IResourceRoom<Unit> = safeDbCall {
        sheetDao.deleteSheet(item)
    }
}