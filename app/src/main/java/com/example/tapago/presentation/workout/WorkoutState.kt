package com.example.tapago.presentation.workout

import com.example.tapago.domain.model.Sheet

data class WorkoutState(

    val isLoanding: Boolean = true,
    val sheet: List<Sheet>? = null
)
