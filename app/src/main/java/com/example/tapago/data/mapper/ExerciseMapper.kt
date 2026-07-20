package com.example.tapago.data.mapper

import com.example.tapago.data.entities.ExercisesEntity
import com.example.tapago.domain.model.Exercise

fun ExercisesEntity.toDomain(): Exercise {
    return Exercise(
        idExerceise = this.exerciseId,
        nameExercise = this.nameExercise,
        typeExercise = this.typeExrcise
    )
}