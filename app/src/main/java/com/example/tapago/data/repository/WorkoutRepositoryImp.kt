package com.example.tapago.data.repository

import androidx.room.withTransaction
import com.example.tapago.AppDatabase
import com.example.tapago.data.daos.ExerciseSheetDao
import com.example.tapago.data.daos.SetExerciseSheetDao
import com.example.tapago.data.daos.SheetDao
import com.example.tapago.data.entities.ExercisesSheetEntity
import com.example.tapago.data.entities.SetsExerciseSheetsEntity
import com.example.tapago.data.entities.SheetsEntity
import com.example.tapago.data.mapper.toDomain
import com.example.tapago.data.utils.safeDbCall
import com.example.tapago.domain.model.Sheet
import com.example.tapago.domain.model.workout.Workout
import com.example.tapago.domain.repository.ITaPagoRepository
import com.example.tapago.domain.wrapper.IResourceRoom

private const val ONE_MINUTE = 60_000L
private const val ONE_HOUR = ONE_MINUTE * 60

class WorkoutRepositoryImp(
    private var database: AppDatabase,
    private var sheetDao: SheetDao,
    private var exerciseSheetDao: ExerciseSheetDao,
    private var setsExerciseDao: SetExerciseSheetDao,

    var sheetInProgress: Sheet? = null
) : ITaPagoRepository<SheetsEntity> {

    override suspend fun select(): IResourceRoom<List<SheetsEntity>> = safeDbCall {
        sheetDao.findAll()
    }

    suspend fun selectSheet(): IResourceRoom<List<Sheet>> {
        val sheet = sheetDao.findAll()

        return safeDbCall { sheet.map { it.toDomain() } }
    }

    suspend fun createSheet(workout: Workout): IResourceRoom<Unit> {
        return try {
            val sheetEntity = SheetsEntity(
                nameSheet = workout.nameSheet,
                qtdExercise = workout.listExercise.size,
                workoutDay = workout.dayWeek,
            )

            database.withTransaction {
                val sheetId = sheetDao.insertSheet(sheetEntity)

                val sheetBodyList = workout.listExercise.map { exercise ->
                    ExercisesSheetEntity(
                        exerciseId = exercise.idExercise,
                        sheetId = sheetId.toInt()
                    )
                }

                val idGenerate = exerciseSheetDao.insertExercisesSheet(sheetBodyList)

                val setsExercise =
                    workout.listExercise.zip(idGenerate).flatMap { (exercise, exerciseId) ->

                        (1..exercise.qtdSets).map { numeroDaSerie ->

                            SetsExerciseSheetsEntity(
                                numSet = numeroDaSerie,
                                numReps = exercise.listSets.firstOrNull()?.numRep ?: 0,
                                weight = exercise.listSets.firstOrNull()?.wheght ?: 0.0,
                                exerciseSheetId = exerciseId.toInt()
                            )
                        }
                    }

                setsExerciseDao.insert(setsExercise)
            }

            IResourceRoom.Success(Unit)

        } catch (e: Exception) {
            IResourceRoom.Error(e.message ?: "Erro ao salvar a planilha")
        }
    }

    suspend fun getExerciseSheet(itemSheet: Int): IResourceRoom<Workout> {
        val result = exerciseSheetDao.getExerciseSheet(itemSheet)

        return safeDbCall { result.toDomain() }
    }

    suspend fun getSheetProgress(): IResourceRoom<Unit> {
        return try {
            val result = sheetDao.getSheetProgress()

            sheetInProgress = result.toDomain()

            IResourceRoom.Success(Unit)

        } catch (e: Exception) {
            IResourceRoom.Error(e.message ?: "Erro para consultar treino em progresso")
        }
    }

    suspend fun progressWorkout(): IResourceRoom<Sheet?> {
        return safeDbCall {
            sheetDao.progressWorkout()?.toDomain().also { sheet ->
                sheetInProgress = sheet
            }
        }
    }

    suspend fun startWorkout(idSheet: Int): IResourceRoom<Int> {
        return safeDbCall {
            sheetDao.startWorkout(idSheet)
        }
    }

    suspend fun getWorkoutDay(currentDay: String): IResourceRoom<Sheet?> {
        return safeDbCall {
            sheetDao.getSheetDay(currentDay)?.toDomain()
        }
    }

    override suspend fun insert(
        item: SheetsEntity
    ): IResourceRoom<Unit> = safeDbCall {
        sheetDao.insertSheet(item)
    }

    suspend fun finishWorkout(idSheet: Int): IResourceRoom<Int> {
        return safeDbCall {
            sheetInProgress = null
            sheetDao.finishWokrout(idSheet)
        }
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