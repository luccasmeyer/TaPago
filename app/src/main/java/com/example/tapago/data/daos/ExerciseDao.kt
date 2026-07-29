package com.example.tapago.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tapago.data.entities.ExercisesEntity

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercises")
    suspend fun findAll(): List<ExercisesEntity>

    @Insert
    suspend fun insertExercise(exercisesEntity: ExercisesEntity)

    @Delete
    suspend fun deleteExercise(exercisesEntity: ExercisesEntity)

    @Query("SELECT * FROM exercises WHERE nameExercise LIKE '%' || :searchItem || '%'")
    suspend fun searchExercise(searchItem: String): List<ExercisesEntity>

    @Query("SELECT nameExercise FROM exercises WHERE :idExercise")
    suspend fun getNameExercise(idExercise: List<Int>): List<String>
}