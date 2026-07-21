package com.example.tapago.data.daos

import androidx.annotation.IntRange
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tapago.data.entities.ExercisesEntity
import com.example.tapago.data.entities.ExercisesSheetEntity
import com.example.tapago.domain.model.workout.WorkoutExercise

@Dao
interface ExerciseSheetDao {

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