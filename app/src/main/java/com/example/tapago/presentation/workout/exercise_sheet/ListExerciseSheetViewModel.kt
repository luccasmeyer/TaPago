package com.example.tapago.presentation.workout.exercise_sheet

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListExerciseSheetViewModel(): ViewModel() {
    private var _uiState = MutableStateFlow(ListExerciseSheetState())
    val uiState: StateFlow<ListExerciseSheetState> = _uiState.asStateFlow()


}