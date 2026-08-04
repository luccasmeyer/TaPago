package com.example.tapago.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tapago.domain.model.workout.Workout

@Entity("workout_complete")
data class WorkoutCompleteEntity(
    @PrimaryKey(autoGenerate = true) val workoutId: Int = 0,
    @ColumnInfo("workout") val workout: Workout,
    @ColumnInfo("completion_data") val completionData: Long = System.currentTimeMillis()
)
