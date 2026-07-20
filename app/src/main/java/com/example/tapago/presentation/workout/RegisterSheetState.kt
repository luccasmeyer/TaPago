package com.example.tapago.presentation.workout

import com.example.tapago.domain.model.Exercise

data class RegisterSheetState(

    val isLoading: Boolean = true,
    val listExercise: List<Exercise> = emptyList()
)