package com.example.tapago.domain.model.workout

data class Workout(

    val nameSheet: String,
    val qtdExercise: Int,
    val dayWeek: String,
    val listExercise: List<WorkoutExercise>
)