package com.example.tapago.data.mapper

import com.example.tapago.data.entities.ExercisesSheetEntity
import com.example.tapago.domain.model.workout.WorkoutExercise

fun ExercisesSheetEntity.toDomain(nameExercise: List<String>): WorkoutExercise{
    return WorkoutExercise(
        idExercise = this.exerciseId,
        nameExercise = nameExercise.toString(),
        qtdSets = this.qtdSets,
        listSets = emptyList()
    )
}

