package com.example.tapago.presentation.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tapago.data.repository.WorkoutRepositoryImp
import com.example.tapago.domain.wrapper.IResourceRoom
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListSheetsViewModel(
    private var repo: WorkoutRepositoryImp
) : ViewModel() {

    private var _uiState = MutableStateFlow(ListSheetsState())
    val uiState: StateFlow<ListSheetsState> = _uiState.asStateFlow()

    fun onNavigationDone() {
        _uiState.update {
            it.copy(
                isError = false,
                message = null,
                navigateToSheetId = null,
                sheets = null
            )
        }
    }

    fun getSheet() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoanding = true
                )
            }

            when (
                val result = repo.selectSheet()
            ) {
                is IResourceRoom.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoanding = false,
                            sheets = result.data
                        )
                    }
                }

                is IResourceRoom.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoanding = false,
                        )
                    }
                }
            }
        }
    }

    fun beginWorkout(idSheet: Int) {
        viewModelScope.launch {
            val progressWorkout = repo.progressWorkout()

            if (progressWorkout is IResourceRoom.Error) {
                _uiState.update {
                    it.copy(
                        isError = true,
                        message = progressWorkout.exception.toString()
                    )
                }
                return@launch
            }

            if (progressWorkout is IResourceRoom.Success) {

                val currentWorkout = progressWorkout.data?.idSheet

                if (progressWorkout.data?.workoutStatus == false || repo.sheetInProgress == null) {
                    val startWorkout = repo.startWorkout(idSheet)

                    if (startWorkout is IResourceRoom.Error) {
                        _uiState.update {
                            it.copy(
                                isError = true,
                                message = "Erro: ${startWorkout.message}, ${startWorkout.exception}"
                            )
                        }
                        return@launch
                    }
                    _uiState.update {
                        it.copy(
                            navigateToSheetId = idSheet
                        )
                    }
                } else if (currentWorkout == idSheet) {
                    _uiState.update {
                        it.copy(
                            navigateToSheetId = idSheet
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            isError = true,
                            message = "Você já tem um treino em andamento"
                        )
                    }
                    onNavigationDone()
                }
            }
        }
    }
}