package com.example.tapago.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tapago.data.entities.SheetsEntity

@Dao
interface SheetDao {

    @Query("SELECT * FROM sheets")
    suspend fun findAll(): List<SheetsEntity>

    @Insert
    suspend fun insertSheet(sheetsEntity: SheetsEntity): Long

    @Delete
    suspend fun deleteSheet(sheetsEntity: SheetsEntity)

    @Query("SELECT * FROM sheets WHERE workoutStatus = 0")
    suspend fun progressWorkout(): Boolean

    @Query("UPDATE sheets SET workoutStatus = 1 where sheetId = :idSheet")
    suspend fun startWorkout(idSheet: Int): Boolean
}