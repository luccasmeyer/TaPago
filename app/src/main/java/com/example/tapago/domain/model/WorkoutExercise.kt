package com.example.tapago.domain.model

data class WorkoutExercise(

    val nameExercise: String,
    val qtdSets: Int,
    val listSets: List<WorkoutSet>
)
