package com.example.tapago.data.mapper

import com.example.tapago.data.daos.ExerciseSheetDao
import com.example.tapago.data.daos.ExerciseSheetDao.SheetComplete
import com.example.tapago.data.entities.SetsExerciseSheetsEntity
import com.example.tapago.domain.model.workout.Workout
import com.example.tapago.domain.model.workout.WorkoutExercise
import com.example.tapago.domain.model.workout.WorkoutSet

fun SheetComplete.toDomain(): Workout{
    return Workout(
        nameSheet = this.sheet.nameSheet.trim(),
        qtdExercise = this.exercises.size,
        listExercise = this.exercises.map { exerciseSheetComplete ->
            exerciseSheetComplete.toDomain()
        }
    )
}

fun ExerciseSheetDao.ExerciseSheetComplete.toDomain(): WorkoutExercise{
    return WorkoutExercise(
        idExercise = this.exercise.exerciseId,
        nameExercise = this.exercise.nameExercise.trim(),
        qtdSets = this.sets.size,
        listSets = this.sets.map { setsExerciseSheetsEntity ->
            setsExerciseSheetsEntity.toDomain()
        }
    )
}

fun SetsExerciseSheetsEntity.toDomain(): WorkoutSet{
    return WorkoutSet(
        numSet = this.numSet, // preciso corrigir isso depois
        numRep = this.numReps,
        wheght = this.weight
    )
}

