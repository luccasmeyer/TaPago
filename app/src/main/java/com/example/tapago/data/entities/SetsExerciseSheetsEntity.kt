package com.example.tapago.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    "sets_exercise_sheet",
    foreignKeys = [
        ForeignKey(
            ExercisesSheetEntity::class,
            ["exerciseSheetId"],
            ["exerciseSheetId"],
            ForeignKey.CASCADE
        )
    ]
)
data class SetsExerciseSheetsEntity(
    @PrimaryKey(autoGenerate = true) val setId: Int = 0,
    @ColumnInfo("numSet") val numSet: Int,
    @ColumnInfo("numReps") val numReps: Int,
    @ColumnInfo("weight") val weight: Double,
    @ColumnInfo("exerciseSheetId") val exerciseSheetId: Int
)
