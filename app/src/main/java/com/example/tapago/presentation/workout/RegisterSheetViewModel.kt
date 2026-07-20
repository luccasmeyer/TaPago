package com.example.tapago.presentation.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tapago.data.repository.ExerciseRepositoryImp
import com.example.tapago.data.repository.WorkoutRepositoryImp
import com.example.tapago.domain.model.Exercise
import com.example.tapago.domain.model.workout.Workout
import com.example.tapago.domain.model.workout.WorkoutExercise
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

    fun createSheet(workout: Workout){
        viewModelScope.launch {
            repo.createSheet(workout)
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
                        listSearchExercise = result.data
                    ) }
                }
            }
        }
    }

    fun addExerciseToSheet(exercise: Exercise) {
        val currentList = _uiState.value.addedExercises.toMutableList()

        val itExists = currentList.any { it.nameExercise == exercise.nameExercise }

        if (!itExists) {
            val newExercise = WorkoutExercise(
                nameExercise = exercise.nameExercise,
                qtdSets = 0,
                listSets = emptyList(),
                idExercise = exercise.idExerceise,
            )

            currentList.add(newExercise)

            _uiState.update { it.copy(
                addedExercises = currentList
            ) }
        }
    }

    fun removeExerciseToSheet(exercise: WorkoutExercise){
        val currentList  = _uiState.value.addedExercises.toMutableList()

        currentList.remove(exercise)

        _uiState.update { it.copy(
            addedExercises = currentList
        ) }
    }

    fun incrementSet(exercise: WorkoutExercise){
        val currentList = _uiState.value.addedExercises.toMutableList()

        val index = currentList.indexOfFirst { it.nameExercise == exercise.nameExercise }

        if (index != -1){
            val exerciseUpdate = exercise.copy(qtdSets = exercise.qtdSets + 1)
            currentList[index] = exerciseUpdate

            _uiState.update { it.copy(
                addedExercises = currentList
            ) }
        }
    }

    fun decrementSet(exercise: WorkoutExercise){
        val currentList = _uiState.value.addedExercises.toMutableList()

        val index = currentList.indexOfFirst { it.nameExercise == exercise.nameExercise }

        if (index != -1 && exercise.qtdSets > 0){
            val exerciseUpdate = exercise.copy(qtdSets = exercise.qtdSets-+ 1)
            currentList[index] = exerciseUpdate

            _uiState.update { it.copy(
                addedExercises = currentList
            ) }
        }
    }
}