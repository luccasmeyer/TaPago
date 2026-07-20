package com.example.tapago.presentation.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tapago.data.repository.ExerciseRepositoryImp
import com.example.tapago.data.repository.WorkoutRepositoryImp
import com.example.tapago.domain.model.Exercise
import com.example.tapago.domain.wrapper.IResourceRoom
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterSheetViewModel(
    private var repo: WorkoutRepositoryImp,
    private var repoExercise: ExerciseRepositoryImp
) : ViewModel() {

    private var _uiState = MutableStateFlow(RegisterSheetState())
    val uiState: StateFlow<RegisterSheetState> = _uiState.asStateFlow()

    fun createSheet(listExercise: List<Exercise>, nameSheet: String){
        viewModelScope.launch {
            repo.createSheet()
        }
    }

    fun searchExercise(searchItem: String){
        viewModelScope.launch {
            val result = repoExercise.searchExercise(searchItem)

            when(result){
                is IResourceRoom.Error -> {
                    _uiState.update { it.copy(
                        isLoading = false
                    ) }
                }
                is IResourceRoom.Success -> {
                    _uiState.update { it.copy(
                        isLoading = false,
                        listExercise = result.data
                    ) }
                }
            }
        }
    }

    fun addExerciseToSheet(exercise: Exercise) {
        val currentList = _uiState.value.addedExercises.toMutableList()

        if (!currentList.contains(exercise)) {
            currentList.add(exercise)
            _uiState.update { it.copy(
                addedExercises = currentList
            ) }
        }
    }
}