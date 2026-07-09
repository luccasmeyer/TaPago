package com.example.tapago.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("exercises")
data class ExercisesEntity(
    @PrimaryKey(autoGenerate = true) val exerciseId: Int = 0,
    @ColumnInfo("nameExercise") val nameExercise: String,
    @ColumnInfo("typeExrcise") val typeExrcise: String
)
