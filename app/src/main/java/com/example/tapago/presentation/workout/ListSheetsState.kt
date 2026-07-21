package com.example.tapago.presentation.workout

import com.example.tapago.domain.model.Sheet

data class ListSheetsState(

    val isLoanding: Boolean = true,
    val sheets: List<Sheet>? = null
)
