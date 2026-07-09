package com.example.tapago.data.daos

import androidx.room.Dao
import androidx.room.Query
import com.example.tapago.data.entities.ExercisesEntity

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercises")
    suspend fun findAll(): List<ExercisesEntity>
}