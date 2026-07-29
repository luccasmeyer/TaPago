package com.example.tapago.data.daos

import androidx.annotation.IntRange
import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.Junction
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import com.example.tapago.data.entities.ExercisesEntity
import com.example.tapago.data.entities.ExercisesSheetEntity
import com.example.tapago.data.entities.SetsExerciseSheetsEntity
import com.example.tapago.data.entities.SheetsEntity
import com.example.tapago.domain.model.Exercise
import com.example.tapago.domain.model.Sheet
import com.example.tapago.domain.model.workout.WorkoutExercise

@Dao
interface ExerciseSheetDao {

    data class ExerciseSheetComplete(
        @Embedded val exerciseSheet: ExercisesSheetEntity,
        @Relation(
            parentColumn = "exerciseId",
            entityColumn = "exerciseId"
        )
        val exercise: ExercisesEntity,

        @Relation(
            parentColumn = "exerciseSheetId",
            entityColumn = "exerciseSheetId"
        )
        val sets: List<SetsExerciseSheetsEntity>
    )

    data class SheetComplete(
        @Embedded val sheet: SheetsEntity,
        @Relation(
            parentColumn = "SheetId",
            entityColumn = "SheetId"
        )
        val exercises: List<ExerciseSheetComplete>
    )

    @Query("SELECT * FROM exercises_sheet")
    suspend fun findAll(): List<ExercisesSheetEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercisesSheet(sheetBodyList: List<ExercisesSheetEntity>)

    @Transaction
    @Query("SELECT * FROM sheets WHERE sheetId = :itemSheet")
    suspend fun getExerciseSheet(itemSheet: Int): SheetComplete
}