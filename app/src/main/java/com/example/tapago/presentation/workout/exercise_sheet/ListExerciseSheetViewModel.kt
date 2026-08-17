package com.example.tapago.presentation.workout.exercise_sheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tapago.data.repository.WorkoutRepositoryImp
import com.example.tapago.domain.wrapper.IResourceRoom
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListExerciseSheetViewModel(
    private var repo: WorkoutRepositoryImp
) : ViewModel() {
    private var _uiState = MutableStateFlow(ListExerciseSheetState())
    val uiState: StateFlow<ListExerciseSheetState> = _uiState.asStateFlow()

    fun getWorkout(idSheet: Int) {
        viewModelScope.launch {
            when (val result = repo.getExerciseSheet(idSheet)) {
                is IResourceRoom.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoanding = false,
                            workout = result.data
                        )
                    }
                }

                is IResourceRoom.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoanding = false,
                            message = "Erro para consultar o treino ${result.message}"
                        )
                    }
                }
            }
        }
    }

    fun finishWorkout(idSheet: Int) {
        viewModelScope.launch {
            repo.finishWorkout(idSheet)

//            if (result is IResourceRoom.Error){
//                _uiState.update { it.copy(
//                    isError = true,
//                    message = result.message
//                ) }
//            }

        }
    }
}