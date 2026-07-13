package com.example.tapago.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    "exercises_sheet",
    foreignKeys = [
        ForeignKey(
            ExercisesEntity::class,
            ["exerciseId"],
            ["exerciseId"],
            ForeignKey.CASCADE
        ),
        ForeignKey(
            SheetsEntity::class,
            ["sheetId"],
            ["sheetId"],
            ForeignKey.CASCADE
        )
    ]
)
data class ExercisesSheetEntity(
    @PrimaryKey(autoGenerate = true) val exerciseSheetId: Int = 0,
    @ColumnInfo("exerciseId") val exerciseId: Int,
    @ColumnInfo("sheetId") val sheetId: Int,
    @ColumnInfo("quantSets") val qtdSets: Int
)
