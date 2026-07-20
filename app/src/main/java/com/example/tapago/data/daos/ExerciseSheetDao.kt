package com.example.tapago.data.daos

import androidx.annotation.IntRange
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tapago.data.entities.ExercisesSheetEntity

@Dao
interface ExerciseSheetDao {

    @Query("SELECT * FROM exercises_sheet")
    suspend fun findAll(): List<ExercisesSheetEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercisesSheet(sheetBodyList: List<ExercisesSheetEntity>) // ✅ Correto (espera a lista)
}