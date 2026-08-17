package com.example.tapago.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tapago.data.entities.SheetsEntity
import com.example.tapago.domain.model.Sheet

@Dao
interface SheetDao {

    @Query("SELECT * FROM sheets")
    suspend fun findAll(): List<SheetsEntity>

    @Insert
    suspend fun insertSheet(sheetsEntity: SheetsEntity): Long

    @Delete
    suspend fun deleteSheet(sheetsEntity: SheetsEntity)

    @Query("SELECT * FROM sheets WHERE workoutStatus = 1")
    suspend fun progressWorkout(): List<SheetsEntity>

    @Query("UPDATE sheets SET workoutStatus = 1, start_time = strftime('%s', 'now') where sheetId = :idSheet")
    suspend fun startWorkout(idSheet: Int): Int

    @Query("SELECT * FROM sheets WHERE workoutStatus = 1")
    suspend fun getSheetProgress(): SheetsEntity
}