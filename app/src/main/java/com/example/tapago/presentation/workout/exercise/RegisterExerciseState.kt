package com.example.tapago.presentation.workout.exercise

data class RegisterExerciseState(

    val nameExercise: String? = null,
    val muscleGroup: String? = null,
    val message: String = "",
    val success: Boolean? = null
)