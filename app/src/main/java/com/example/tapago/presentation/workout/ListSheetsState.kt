package com.example.tapago.presentation.workout

import com.example.tapago.domain.model.Sheet

data class ListSheetsState(

    val isLoanding: Boolean = true,
    val isError: Boolean = false,
    val message: String? = null,
    val sheets: List<Sheet>? = null
)
