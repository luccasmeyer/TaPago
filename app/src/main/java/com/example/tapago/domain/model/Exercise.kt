package com.example.tapago.domain.model

import com.example.tapago.domain.common.TypeExercise

data class Exercise(

    val idExerceise: Long,
    val nameExercise: String,
    val typeExercise: TypeExercise
)
