package com.example.tapago.presentation.menu

import com.example.tapago.domain.model.Sheet

data class MenuState(

    val isLoading: Boolean? = null,
    val isError: Boolean = false,
    val message: String? = null,
    val sheetDay: Sheet? = null
)
