package com.example.tapago.data.daos

import androidx.room.Dao
import androidx.room.Insert
import com.example.tapago.data.entities.SetsExerciseSheetsEntity

@Dao
interface SetExerciseSheetDao {

    @Insert
    suspend fun insert(setsExerciseSheetsEntity: List<SetsExerciseSheetsEntity>)

}