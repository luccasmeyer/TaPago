package com.example.tapago.domain.model

data class Sheet(

    val idSheet: Int,
    val nameSheet: String,
    val qtdExercise: Int,
    val workoutStatus: Boolean?,
    val workoutDay: String
)