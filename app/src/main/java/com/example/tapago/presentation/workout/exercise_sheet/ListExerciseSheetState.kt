package com.example.tapago.presentation.workout.exercise_sheet

import com.example.tapago.domain.model.workout.Workout

data class ListExerciseSheetState(

    val isLoanding: Boolean = true,
    val isError: Boolean = false,
    val message:String? = null,
    val workout: Workout? = null
)