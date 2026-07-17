package com.example.tapago.presentation.workout.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tapago.data.entities.ExercisesEntity
import com.example.tapago.data.repository.ExerciseRepositoryImp
import com.example.tapago.domain.wrapper.IResourceRoom
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterExerciseViewModel(
    private var repo: ExerciseRepositoryImp
): ViewModel() {

    private var _uiState = MutableStateFlow(RegisterExerciseState())
    val uiState: StateFlow<RegisterExerciseState> = _uiState.asStateFlow()

    fun registerExercise(item: ExercisesEntity){
        viewModelScope.launch {
               when(val result = repo.insert(item)){
                   is IResourceRoom.Success -> {
                       _uiState.update { it.copy(
                           message = "Exercício cadastrado",
                           success = true
                       ) }
                   }
                   is IResourceRoom.Error -> {
                       _uiState.update { it.copy(
                           message = "Erro para cadastrar ${result.message}",
                           success = false
                       ) }
                   }
               }
        }
    }
}