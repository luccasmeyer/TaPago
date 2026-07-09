package com.example.tapago.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("sheets")
data class SheetsEntity(
    @PrimaryKey(autoGenerate = true) val sheetId: Int = 0,
    @ColumnInfo("nameSheet") val nameSheet: String,
    @ColumnInfo("quantExercise") val qtdExercise: Int
)
