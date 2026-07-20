package com.example.tapago.domain.model.workout

data class WorkoutExercise(

    val idExercise: Int,
    val nameExercise: String,
    val qtdSets: Int,
    val listSets: List<WorkoutSet>
)
