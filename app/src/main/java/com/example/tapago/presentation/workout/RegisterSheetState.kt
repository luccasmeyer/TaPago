package com.example.tapago.presentation.workout

import com.example.tapago.domain.model.Exercise
import com.example.tapago.domain.model.workout.WorkoutExercise

data class RegisterSheetState(
    val isLoading: Boolean = true,
    val listExercise: List<Exercise> = emptyList(),
    val addedExercises: List<WorkoutExercise> = emptyList()
)