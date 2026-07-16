package com.example.tapago.data.mapper

import com.example.tapago.data.entities.SheetsEntity
import com.example.tapago.domain.model.Sheet

fun SheetsEntity.toDomain(): Sheet{
    return Sheet(
        idSheet = this.sheetId,
        nameSheet = this.nameSheet,
        qtdExercise = this.qtdExercise
    )
}