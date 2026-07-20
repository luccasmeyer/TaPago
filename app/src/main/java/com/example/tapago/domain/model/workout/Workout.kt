package com.example.tapago.domain.model.workout

data class Workout (

    val nameSheet: String,
    val qtdExercise: Int,
    val listExercise: List<WorkoutExercise>
)