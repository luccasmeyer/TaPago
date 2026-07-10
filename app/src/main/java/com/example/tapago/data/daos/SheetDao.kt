package com.example.tapago.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tapago.data.entities.SheetsEntity

@Dao
interface SheetDao {

    @Query("SELECT * FROM sheets")
    suspend fun findAll(): List<SheetsEntity>

    @Insert
    suspend fun insertSheet(sheetsEntity: SheetsEntity)

    @Delete
    suspend fun deleteSheet(sheetsEntity: SheetsEntity)
}