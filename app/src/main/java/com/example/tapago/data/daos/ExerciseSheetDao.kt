package com.example.tapago.data.daos

import androidx.annotation.IntRange
import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.Junction
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Relation
import com.example.tapago.data.entities.ExercisesEntity
import com.example.tapago.data.entities.ExercisesSheetEntity
import com.example.tapago.data.entities.SetsExerciseSheetsEntity
import com.example.tapago.data.entities.SheetsEntity
import com.example.tapago.domain.model.Exercise
import com.example.tapago.domain.model.Sheet
import com.example.tapago.domain.model.workout.WorkoutExercise

@Dao
interface ExerciseSheetDao {

    data class SheetWithExercisesSetup(
        @Embedded val sheet: SheetsEntity,
        @Relation(
            parentColumn = "sheetId",
            entityColumn = "sheetId",
        )
        val exercise: List<ExercisesSheetEntity>
    )

    data class ExerciseWithSetsSetup(
        @Embedded val exerciseSheet: ExercisesSheetEntity,
        @Relation(
            parentColumn = "exerciseId",
            entityColumn = "exerciseId",
            associateBy = Junction(SetsExerciseSheetsEntity::class)
        )
        val setsExercise: List<SetsExerciseSheetsEntity>
    )

    data class ExerciseWithDetalisSetup(
        @Embedded val exercise: ExercisesSheetEntity,
        @Relation(
            parentColumn = "exerciseId",
            entityColumn = "exerciseId",
            associateBy = Junction(ExercisesSheetEntity::class)
        )
        val exerciseDetalis: List<ExercisesEntity>
    )

    @Query("SELECT * FROM exercises_sheet")
    suspend fun findAll(): List<ExercisesSheetEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercisesSheet(sheetBodyList: List<ExercisesSheetEntity>)

    @Query("""
    SELECT 
        * 
    FROM 
        EXERCISES_SHEET A
    INNER JOIN SHEETS B
    ON A.sheetId = B.sheetId
    INNER JOIN EXERCISES C 
    ON A.exerciseId = C.exerciseId
    WHERE 
        B.sheetId = :itemSheet
""")
    suspend fun getExerciseSheet(itemSheet: Int): List<ExercisesSheetEntity>
}